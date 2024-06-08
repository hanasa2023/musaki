package features.home.components.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import features.home.components.searchbar.MusakiSearchBarUi
import musaki.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource

@Composable
fun MusakiTopBar() {
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

            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = null,
            )

            Row(
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Icon(
                    painter = painterResource(Res.drawable.remove),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Icon(
                    painter = painterResource(Res.drawable.crop_7_5),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Icon(
                    painter = painterResource(Res.drawable.close),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}