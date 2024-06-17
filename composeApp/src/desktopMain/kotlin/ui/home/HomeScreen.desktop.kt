package ui.home

import androidx.compose.runtime.Composable


@Composable
actual fun HomeScreenUi(
    onClose: () -> Unit,
    onMinimize: () -> Unit,
    onMaximize: () -> Unit
) {
//	MaterialTheme {
//		Scaffold(
//			bottomBar = { MusakiBottomBar() }
//		) {
//			Row(
//				modifier = Modifier
//					.background(Color.Blue)
//					.fillMaxSize()
//			) {
//				Column(
//					modifier = Modifier
//						.fillMaxWidth(.2f)
//				) {
//					MusakiSideBar(navController)
//				}
//
//				Column(
//					modifier = Modifier
//						.fillMaxWidth()
//						.background(Color.Green)
//				) {
//					MusakiTopBar(
//						onClose = onClose,
//						onMinimize = onMinimize,
//						onMaximize = onMaximize
//					)
//					MusakiPage()
//				}
//			}
//		}
//	}
}