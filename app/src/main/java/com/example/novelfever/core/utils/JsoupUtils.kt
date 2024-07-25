package com.example.novelfever.core.utils

import android.content.Context
import com.example.novelfever.core.sourceutils.AndroidCookieJar
import com.example.novelfever.core.sourceutils.CloudflareInterceptor
import com.example.novelfever.core.sourceutils.DdosGuardInterceptor
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.util.concurrent.TimeUnit

object JsoupUtils {
    private val httpClient = OkHttpClient.Builder()
        .cookieJar(AndroidCookieJar())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .callTimeout(2, TimeUnit.MINUTES)
        .build()

    fun getJsoup(
        url: String,
        mapOfHeaders: Map<String, String>? = null
    ): Document {
        val requestBuilder = Request.Builder().url(url)
        if (!mapOfHeaders.isNullOrEmpty()) {
            mapOfHeaders.forEach {
                requestBuilder.addHeader(it.key, it.value)
            }
        }
        return Jsoup.parse(
            httpClient.newCall(requestBuilder.build())
                .execute().body!!.string()
        )
    }

    fun getJsoupWithCloudflare(
        url: String,
        context: Context,
        mapOfHeaders: Map<String, String>? = null
    ): Document {
        val httpClient = httpClient.newBuilder()
            .addInterceptor(CloudflareInterceptor(context))
            .build()

        val requestBuilder = Request.Builder().url(url)
        if (!mapOfHeaders.isNullOrEmpty()) {
            mapOfHeaders.forEach {
                requestBuilder.addHeader(it.key, it.value)
            }
        }

        val request = requestBuilder.build()
        return Jsoup.parse(httpClient.newCall(request).execute().body!!.string())
    }

    fun getJsoupWithDdosGuard(
        url: String,
        mapOfHeaders: Map<String, String>? = null
    ): Document {
        val httpClient = httpClient.newBuilder()
            .addInterceptor(DdosGuardInterceptor(httpClient))
            .build()

        val requestBuilder = Request.Builder().url(url)
        if (!mapOfHeaders.isNullOrEmpty()) {
            mapOfHeaders.forEach {
                requestBuilder.addHeader(it.key, it.value)
            }
        }
        return Jsoup.parse(
            httpClient.newCall(requestBuilder.build())
                .execute().body!!.string()
        )
    }

}