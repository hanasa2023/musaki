package ui.login.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.WindowScope
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import musaki.composeapp.generated.resources.Res
import musaki.composeapp.generated.resources.close
import musaki.composeapp.generated.resources.musaki
import musaki.composeapp.generated.resources.refresh
import org.jetbrains.compose.resources.painterResource
import ui.home.components.utils.MusakiIconButtonBig
import ui.login.components.base64Image.Base64Image

@Composable
fun WindowScope.CodeLogin(
    navController: NavController,
    onClose: () -> Unit,
    qrResource: String,
    loginCode: Int,
    onRefreshCode: suspend () -> Unit,
    stopTimer: () -> Unit,
    init: suspend () -> Unit
) {
    LaunchedEffect(loginCode) {
        when (loginCode) {
            803 -> {
                stopTimer()
                onClose()
            }

            0 -> init()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.inversePrimary)
            .padding(horizontal = 16.dp)
    ) {
        WindowDraggableArea {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MusakiIconButtonBig(
                    resource = Res.drawable.close,
                    contentDescription = null,
                    onClick = {
                        stopTimer()
                        onClose()
                    }
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "扫码登录",
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(32.dp))
            //显示二维码
            if (qrResource.isNotEmpty()) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    if (loginCode == 800) {
                        Base64Image(
                            resource = qrResource,
                            modifier = Modifier.size(200.dp),
                            colorFilter = ColorFilter.tint(Color.Gray, BlendMode.Modulate)
                        )
                        IconButton(
                            onClick = {
                                CoroutineScope(Dispatchers.IO).launch {
                                    onRefreshCode()
                                }
                            }
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.refresh),
                                contentDescription = null,
                                tint = Color.Cyan,
                                modifier = Modifier.size(80.dp)
                            )
                        }
                    } else
                        Base64Image(
                            resource = qrResource,
                            modifier = Modifier.size(200.dp),
                        )
                }
            } else
                Image(
                    painter = painterResource(Res.drawable.musaki),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )

            Spacer(modifier = Modifier.height(32.dp))

            TextButton(
                onClick = {
                    navController.navigate("phoneLogin") {
                        stopTimer()
                        navController.graph.startDestinationRoute?.let { popUpTo(it) }
                        launchSingleTop = true
                    }
                }
            ) {
                Text(
                    "选择其他登录方式>",
                    fontSize = 12.sp
                )
            }
        }
    }
}