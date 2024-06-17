package ui.home.components.searchbar

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ui.home.components.utils.MusakiIconButton
import musaki.composeapp.generated.resources.Res
import musaki.composeapp.generated.resources.arrow_back

@Composable
fun MusakiSearchBarUi(
	viewModel: MusakiSearchBarViewModel = viewModel(),
) {
    val interactionSource by viewModel.interactionSource.collectAsState()

    MusakiSearchBar()
}

//TODO:添加点击搜索框聚焦
@Composable
fun MusakiSearchBar(
) {
    var text by remember { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
            MusakiIconButton(
                resource = Res.drawable.arrow_back,
                contentDescription = "back to previous screen"
            )

        BasicTextField(
            value = text,
            onValueChange = { text = it },
            maxLines = 1,
            modifier = Modifier
                .width(240.dp)
                .padding(horizontal = 4.dp, vertical = 8.dp),
        ) { innerTextField ->
            Row(
                modifier = Modifier
                    .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = ""
                )

                if (text.isEmpty())
                    Text("Search")
                else
                    innerTextField()
            }
        }
    }
}