package features.login.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.WindowScope
import androidx.navigation.NavController
import features.home.components.utils.MusakiIconButtonBig
import musaki.composeapp.generated.resources.Res
import musaki.composeapp.generated.resources.close

@Composable
fun WindowScope.PhoneLogin(
    navController: NavController,
    onClose: () -> Unit,
    onAccountChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {
    var currentAccount by remember { mutableStateOf("") }
    var currentPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.inversePrimary)
            .border(1.dp, MaterialTheme.colorScheme.onPrimaryContainer)
            .padding(horizontal = 16.dp)
    ) {
        Box {
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
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = {}
                    ) {
                        Text(
                            text = "密码登录",
                            fontSize = 12.sp,
                            textDecoration = TextDecoration.Underline
                        )
                    }

                    TextButton(
                        onClick = {}
                    ) {
                        Text(
                            text = "注册？",
                            fontSize = 12.sp,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }

//            Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = onClose
                    ) {
                        Text("取消")
                    }

                    Button(
                        onClick = {
                            onAccountChange(currentAccount)
                            onPasswordChange(currentPassword)
                        }
                    ) {
                        Text("登录")
                    }
                }
            }
        }
    }
}
