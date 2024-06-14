package features.login.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.WindowScope
import androidx.navigation.NavController
import features.home.components.utils.MusakiIconButtonBig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import musaki.composeapp.generated.resources.Res
import musaki.composeapp.generated.resources.close
import musaki.composeapp.generated.resources.qr_code
import org.jetbrains.compose.resources.painterResource

@Composable
fun WindowScope.PhoneLogin(
    navController: NavController,
    autoLogin: Boolean,
    phoneLoginCode: Int,
    updateAutoLoginState: (Boolean) -> Unit,
    onClose: () -> Unit,
    onLogin: suspend () -> Unit,
    onAccountChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {
    var currentAccount by remember { mutableStateOf("") }
    var currentPassword by remember { mutableStateOf("") }

    LaunchedEffect(phoneLoginCode) {
        when (phoneLoginCode) {
            200 -> {
                onClose()
            }

            502 -> {
                //TODO
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.inversePrimary)
            .border(1.dp, MaterialTheme.colorScheme.onPrimaryContainer)
            .padding(horizontal = 16.dp)
    ) {
        Box {
            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(68.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .drawWithContent {
                            val path = Path().apply {
                                moveTo(size.width, 0f)
                                lineTo(0f, size.height)
                                lineTo(size.width, size.height)
                                close()
                            }
                            drawContent()
                            drawPath(
                                path = path,
                                color = Color(0xFFFFB1C0),
                            )
                        }
                        .clickable {
                            navController.navigate("codeLogin") {
                                navController.graph.startDestinationRoute?.let { popUpTo(it) }
                                launchSingleTop = true
                            }
                        }
                ) {
                    Image(
                        painter = painterResource(Res.drawable.qr_code),
                        contentDescription = null
                    )
                }
            }

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
                            onClose()
                        }
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = currentAccount,
                    onValueChange = { currentAccount = it },
                    label = { Text("手机号") },
                    placeholder = { Text("请输入手机号") }
                )

                Spacer(modifier = Modifier.height(40.dp))

                OutlinedTextField(
                    value = currentPassword,
                    onValueChange = { currentPassword = it },
                    label = { Text("密码") },
                    placeholder = { Text("请输入密码") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(.8f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = autoLogin,
                            onCheckedChange = { updateAutoLoginState(it) }
                        )
                        Text(
                            text = "自动登录",
                            fontSize = 12.sp
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TextButton(
                            onClick = {}
                        ) {
                            Text(
                                text = "忘记密码？",
                                fontSize = 12.sp,
                                textDecoration = TextDecoration.Underline
                            )
                        }

                        VerticalDivider(
                            modifier = Modifier.height(10.dp),
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )

                        TextButton(
                            onClick = {},
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            Text(
                                text = "密码登录",
                                fontSize = 12.sp,
                                textDecoration = TextDecoration.Underline
                            )
                        }
                    }
                }

//            Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(.8f)
                ) {
                    Button(
                        onClick = {
                            onAccountChange(currentAccount)
                            onPasswordChange(currentPassword)
                            CoroutineScope(Dispatchers.IO).launch { onLogin() }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("登录")
                    }

                    Button(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("注册")
                    }
                }
            }
        }
    }
}
