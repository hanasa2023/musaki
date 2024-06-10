package features.login.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.WindowScope
import androidx.navigation.NavController
import features.home.components.utils.MusakiIconButtonBig
import features.login.components.base64Image.Base64Image
import musaki.composeapp.generated.resources.Res
import musaki.composeapp.generated.resources.close
import musaki.composeapp.generated.resources.musaki
import org.jetbrains.compose.resources.painterResource

//TODO:进一步提升状态,确保CodeLogin的数据源唯一（遵循单一可信数据来源）
//比如此处的onClose 与 onScanner来自两个不用的数据来源，这会使该可组合项的操作变得极为麻烦
@Composable
fun WindowScope.CodeLogin(
    navController: NavController,
    onClose: () -> Unit,
    qrResource: String,
    onScanner: () -> Unit
) {
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
                    onClick = onClose
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
            if (qrResource.isNotEmpty())
                Base64Image(
                    resource = qrResource,
                    modifier = Modifier.size(200.dp)
                )
            else
                Image(
                    painter = painterResource(Res.drawable.musaki),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )

            Spacer(modifier = Modifier.height(32.dp))

            TextButton(
                onClick = {
                    navController.navigate("phoneLogin") {
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