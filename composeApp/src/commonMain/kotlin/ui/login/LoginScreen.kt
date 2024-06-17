package ui.login

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
expect fun LoginScreen(
	loginViewModel: LoginViewModel = viewModel(),
	onClose: () -> Unit
)