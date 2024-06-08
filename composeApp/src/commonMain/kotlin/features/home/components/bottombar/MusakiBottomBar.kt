package features.home.components.bottombar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import musaki.composeapp.generated.resources.Res
import musaki.composeapp.generated.resources.knd
import org.jetbrains.compose.resources.painterResource

@Composable
fun MusakiBottomBar() {
    Row(
        modifier = Modifier
            .background(Color.Cyan)
            .height(80.dp)
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 10.dp)
    ) {
        Image(
            painter = painterResource(Res.drawable.knd),
            contentDescription = "avatar",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
    }
}