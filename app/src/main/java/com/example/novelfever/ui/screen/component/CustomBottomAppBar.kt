package com.example.novelfever.ui.screen.component

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.novelfever.ui.screen.navigation.Screen


@Composable
fun CustomBottomAppBar(currentPage: Int, onPageSelected: (Int) -> Unit) {
    val items = listOf(
        Screen.Home,
        Screen.Update,
        Screen.Library,
        Screen.History,
        Screen.Setting
    )
    NavigationBar {
        items.forEachIndexed { index, screen ->
            NavigationBarItem(
                selected = currentPage == index,
                onClick = {
                    onPageSelected(index)
                },
                label = {
                    Text(
                        stringResource(screen.resourceId),
                        color = if (currentPage == index)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onBackground
                    )
                },
                icon = {
                    BadgedBox(
                        badge = {
                            if (screen.badgeCount != null) {
                                Badge {
                                    Text(text = screen.badgeCount.toString())
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (currentPage == index)
                                screen.selectedIcon
                            else
                                screen.unselectedIcon,
                            contentDescription = stringResource(screen.resourceId),
                            tint = if (currentPage == index)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            )
        }
    }
}
