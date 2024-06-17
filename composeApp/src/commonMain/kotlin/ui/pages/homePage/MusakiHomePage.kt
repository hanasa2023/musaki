package ui.pages.homePage

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun MusakiHomePage() {
	var count by rememberSaveable { mutableStateOf(0) }

	Column {
		Button(
			onClick = { count++ }
		) {
			Text("add count")
		}

		Text("HomePage $count")
	}
}