package com.example.novelfever.core.sourceutils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.webkit.*
import androidx.core.content.ContextCompat
import com.example.novelfever.ui.component.webview.WebViewActivity
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import java.io.IOException
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


class CloudflareInterceptor(private val context: Context) : Interceptor {
    private val cookieManager = AndroidCookieJar()

    private fun shouldIntercept(response: Response): Boolean {
        return response.code in ERROR_CODES && response.header("Server") in SERVER_CHECK
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (!shouldIntercept(response)) {
            return response
        }
        return intercept(chain, request, response)
    }

    private fun intercept(
        chain: Interceptor.Chain,
        request: Request,
        response: Response
    ): Response {
        try {
            response.close()
            cookieManager.remove(request.url, COOKIE_NAMES, 0)
            resolveWithWebView(request)

            return chain.proceed(request)
        }
        catch (e: CloudflareBypassException) {
            throw IOException()
        } catch (e: Exception) {
            throw IOException(e)
        }
    }

    private fun resolveWithWebView(originalRequest: Request) {
        val requestUrl = originalRequest.url.toString()
        val headers = parseHeaders(originalRequest.headers)

        val intent = Intent(context, WebViewActivity::class.java).apply {
            putExtra("url", requestUrl)
            putExtra("headers", headers as HashMap)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    private fun parseHeaders(headers: Headers): Map<String, String> {
        return headers
            .filter { (name, value) ->
                isRequestHeaderSafe(name, value)
            }
            .groupBy(keySelector = { (name, _) -> name }) { (_, value) -> value }
            .mapValues { it.value.getOrNull(0).orEmpty() }
    }
}

private val ERROR_CODES = listOf(403, 503)
private val SERVER_CHECK = arrayOf("cloudflare-nginx", "cloudflare")
private val COOKIE_NAMES = listOf("cf_clearance")

private fun isRequestHeaderSafe(name: String, value: String): Boolean {
    val nameLower = name.lowercase(Locale.ENGLISH)
    val valueLower = value.lowercase(Locale.ENGLISH)
    if (nameLower in unsafeHeaderNames || nameLower.startsWith("proxy-")) return false
    if (nameLower == "connection" && valueLower == "upgrade") return false
    return true
}

private val unsafeHeaderNames = listOf(
    "content-length",
    "host",
    "trailer",
    "te",
    "upgrade",
    "cookie2",
    "keep-alive",
    "transfer-encoding",
    "set-cookie"
)

private class CloudflareBypassException : Exception()