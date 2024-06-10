package features.home.components.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import features.home.components.searchbar.MusakiSearchBarUi
import features.home.components.utils.MusakiIconButton
import musaki.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import ui.theme.IconSpacer

@Composable
fun MusakiTopBar(
    onClose: () -> Unit,
    onMinimize: () -> Unit,
    onMaximize: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MusakiSearchBarUi()
        Row(verticalAlignment = Alignment.CenterVertically) {
            //User info
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(Res.drawable.knd),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(40.dp)
                        .border(
                            1.dp,
                            Color.Cyan,
                            CircleShape
                        )
                        .clip(CircleShape)
                )

                Text(
                    text = "hanasaki",
                    fontSize = 16.sp
                )
            }

            IconSpacer()

            Row {
                MusakiIconButton(
                    resource = Icons.Filled.Settings,
                    contentDescription = null,
                )
            }

            IconSpacer()

            VerticalDivider(modifier = Modifier.fillMaxHeight(.4f))

            IconSpacer()

            Row(
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                MusakiIconButton(
                    resource = Res.drawable.remove,
                    contentDescription = null,
                    onClick = onMinimize
                )

                IconSpacer()

                MusakiIconButton(
                    resource = Res.drawable.crop_7_5,
                    contentDescription = null,
                    onClick = onMaximize
                )

                IconSpacer()

                MusakiIconButton(
                    resource = Res.drawable.close,
                    contentDescription = null,
                    onClick = onClose
                )
            }
        }
    }
}
