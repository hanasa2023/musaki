package features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import features.home.components.MusakiPage
import features.home.components.MusakiSideBar
import features.home.components.bottombar.MusakiBottomBar
import features.home.components.topbar.MusakiTopBar
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun HomeScreenUi() {
    MaterialTheme {
        Scaffold(
            bottomBar = { MusakiBottomBar() }
        ) {
            Row(
                modifier = Modifier
                    .background(Color.Blue)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(.2f)
                        .background(Color.Red)
                ) {
                    MusakiSideBar()
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Green)
                ) {
                    MusakiTopBar()
                    MusakiPage()
                }
            }
        }
    }
}