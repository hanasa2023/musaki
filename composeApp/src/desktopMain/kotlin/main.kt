import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import androidx.navigation.compose.rememberNavController
import features.home.components.MusakiPage
import features.home.components.bottombar.MusakiBottomBar
import features.home.components.sidebar.MusakiSideBar
import features.home.components.topbar.MusakiTopBar
import musaki.composeapp.generated.resources.Res
import musaki.composeapp.generated.resources.app_name
import musaki.composeapp.generated.resources.musaki
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ui.theme.AppTheme

fun main() = application {
    var isOpen by remember { mutableStateOf(true) }
    val windowState = rememberWindowState(
        size = DpSize(1080.dp, 750.dp),
    )

    if (isOpen) {
        val trayState = rememberTrayState()

        Tray(
            state = trayState,
            //TODO: Change icon
            icon = painterResource(Res.drawable.musaki),
            menu = {
                Item(
                    "Exit",
                    onClick = {
                        isOpen = false
                    }
                )
            }
        )

        Window(
            state = windowState,
            //TODO：change icon
            icon = painterResource(Res.drawable.musaki),
            onCloseRequest = ::exitApplication,
            title = stringResource(Res.string.app_name),
            undecorated = true
        ) {
            AppTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = { MusakiBottomBar() },
                ) { innerPadding ->
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .padding(innerPadding)
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(.2f)
                        ) {
                            MusakiSideBar(navController)
                        }

                        VerticalDivider()

                        Column(
                            modifier = Modifier
                                .weight(.8f)
                        ) {
                            WindowDraggableArea {
                                MusakiTopBar(
                                    onClose = ::exitApplication,
                                    onMinimize = { windowState.isMinimized = windowState.isMinimized.not() },
                                    onMaximize = {
                                        windowState.placement = if (windowState.placement == WindowPlacement.Maximized)
                                            WindowPlacement.Floating else WindowPlacement.Maximized
                                    }
                                )
                            }
                            MusakiPage()
                        }
                    }
                }
            }
        }
    }
}
