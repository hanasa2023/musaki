package ui.login.pages

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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import musaki.composeapp.generated.resources.Res
import musaki.composeapp.generated.resources.close
import musaki.composeapp.generated.resources.qr_code
import org.jetbrains.compose.resources.painterResource
import ui.home.components.utils.MusakiIconButtonBig

@Composable
fun WindowScope.PhoneLogin(
	navController: NavController,
	autoLogin: Boolean,
	phoneLoginCode: Int,
	moveToCodeLogin: () -> Unit,
	updateAutoLoginState: (Boolean) -> Unit,
	sendVerificationCode: suspend (String) -> Unit,
	onClose: () -> Unit,
	onPwdLogin: suspend (String, String) -> Unit,
	onVerLogin: suspend (String, String) -> Unit,
) {
	var currentPhone by remember { mutableStateOf("") }
	var currentPassword by remember { mutableStateOf("") }
	var currentVerificationCode by remember { mutableStateOf("") }
	var isPwdLogin by remember { mutableStateOf(true) }

	LaunchedEffect(phoneLoginCode) {
		when (phoneLoginCode) {
			200 -> {
				onClose()
			}

			502 -> {
				//TODO:账号密码错误，抛出提示
			}

			503 -> {
				//TODO:验证码错误
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
		//TODO: Bug只能在蒙板三角形处可以点击
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
						.clickable(
							indication = null,
							interactionSource = remember { MutableInteractionSource() }
						) {
							moveToCodeLogin()
							navController.navigate("codeLogin") {
								navController.graph.startDestinationRoute?.let { popUpTo(it) }
								launchSingleTop = true
							}
						}
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
					value = currentPhone,
					onValueChange = { currentPhone = it },
					label = { Text("手机号") },
					placeholder = { Text("请输入手机号") }
				)

				Spacer(modifier = Modifier.height(40.dp))

				if (isPwdLogin)
					OutlinedTextField(
						value = currentPassword,
						onValueChange = { currentPassword = it },
						label = { Text("密码") },
						placeholder = { Text("请输入密码") },
						singleLine = true,
						visualTransformation = PasswordVisualTransformation(),
						keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
					)
				else
					OutlinedTextField(
						value = currentVerificationCode,
						onValueChange = { currentVerificationCode = it },
						label = { Text("验证码") },
						placeholder = { Text("请输入验证码") },
						singleLine = true,
						trailingIcon = {
							TextButton(
								onClick = {
									CoroutineScope(Dispatchers.IO).launch {
										sendVerificationCode(currentPhone)
									}
								}
							) {
								Text("发送验证码")
							}
						}
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
							onClick = { isPwdLogin = !isPwdLogin },
						) {
							Text(
								text = if (isPwdLogin) "验证码登录" else "密码登录",
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
							CoroutineScope(Dispatchers.IO).launch {
								if (isPwdLogin)
									onPwdLogin(currentPhone, currentPassword)
								else
									onVerLogin(currentPhone, currentVerificationCode)
							}
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
