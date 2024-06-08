package features.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import musaki.composeapp.generated.resources.Res
import musaki.composeapp.generated.resources.img_mzk
import org.jetbrains.compose.resources.painterResource

@Composable
fun ScrollCard() {
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .padding(8.dp)
            .height(200.dp)
    ) {
        Image(
            painter = painterResource(Res.drawable.img_mzk),
            contentDescription = "image",
        )
    }
}