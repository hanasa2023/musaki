import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import features.home.HomeScreenUi
import musaki.composeapp.generated.resources.Res
import musaki.composeapp.generated.resources.musaki
import org.jetbrains.compose.resources.painterResource

fun main() = application {
    var isOpen by remember { mutableStateOf(true) }
    val windowState = rememberWindowState(size = DpSize(1080.dp, 750.dp))

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
            onCloseRequest = ::exitApplication,
            title = "musaki",
//            undecorated = true
        ) {
            HomeScreenUi()
        }

    }
}