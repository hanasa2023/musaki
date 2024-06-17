package ui.home

import androidx.compose.runtime.Composable


@Composable
expect fun HomeScreenUi(
	onClose: () -> Unit,
	onMinimize: () -> Unit,
	onMaximize: () -> Unit
)