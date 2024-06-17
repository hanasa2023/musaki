package ui.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogWindow
import androidx.compose.ui.window.rememberDialogState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import musaki.composeapp.generated.resources.Res
import musaki.composeapp.generated.resources.musaki
import org.jetbrains.compose.resources.painterResource
import ui.login.pages.CodeLogin
import ui.login.pages.PhoneLogin

@Composable
actual fun LoginScreen(
	loginViewModel: LoginViewModel,
	onClose: () -> Unit,
) {
	val loginNavController = rememberNavController()
	val dialogState = rememberDialogState(size = DpSize(400.dp, 600.dp))
	val qrResource = loginViewModel.qRCodeResource.collectAsState().value
	val loginCode = loginViewModel.loginCode.collectAsState().value
	val phoneLoginCode = loginViewModel.phoneLoginCode.collectAsState().value
	val autoLogin = loginViewModel.autoLogin.collectAsState().value

	DialogWindow(
		title = "codeLogin",
		icon = painterResource(Res.drawable.musaki),
		state = dialogState,
		resizable = false,
		onCloseRequest = onClose,
//        transparent = true,
		undecorated = true
	) {
		Surface(
			modifier = Modifier.fillMaxSize(),
//            shape = RoundedCornerShape(10.dp),
			border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimaryContainer)
		) {
			NavHost(navController = loginNavController, startDestination = "codeLogin") {
				composable("phoneLogin") {
					PhoneLogin(
						navController = loginNavController,
						phoneLoginCode = phoneLoginCode,
						autoLogin = autoLogin,
						moveToCodeLogin = { loginViewModel.resetLoginCode() },
						updateAutoLoginState = { loginViewModel.updateAutoLoginState(it) },
						sendVerificationCode = { loginViewModel.sendVerificationCode(it) },
						onClose = onClose,
						onPwdLogin = { phone, password ->
							loginViewModel.onPwdLogin(phone, password)
						},
						onVerLogin = { phone, verificationCode ->
							loginViewModel.onVerLogin(phone, verificationCode)
						}
					)
				}
				composable("codeLogin") {
					CodeLogin(
						navController = loginNavController,
						onClose = onClose,
						qrResource = qrResource,
						loginCode = loginCode,
						init = { loginViewModel.startCodeTimer() },
						onRefreshCode = { loginViewModel.refreshQRCode() },
						stopTimer = { loginViewModel.stopTimer() }
					)
				}
			}
		}
	}
}

