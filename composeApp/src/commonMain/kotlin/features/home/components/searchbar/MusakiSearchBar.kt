package features.home.components.searchbar

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import musaki.composeapp.generated.resources.Res
import musaki.composeapp.generated.resources.arrow_back
import org.jetbrains.compose.resources.painterResource

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
        Box(
            modifier = Modifier
                .size(24.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { /*TODO*/ }
        ) {
            Icon(
                painter = painterResource(Res.drawable.arrow_back),
                contentDescription = "back to previous screen"
            )
        }

        BasicTextField(
            value = text,
            onValueChange = { text = it },
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