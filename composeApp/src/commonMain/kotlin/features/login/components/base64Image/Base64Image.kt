package features.login.components.base64Image

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
@Composable
fun Base64Image(
    resource: String,
    modifier: Modifier = Modifier,
    colorFilter: ColorFilter? = null
) {
    val decodedBytes = Base64.Default.decode(resource)
    val image = Image.makeFromEncoded(decodedBytes).toComposeImageBitmap()

    if (colorFilter != null)
        Image(
            bitmap = image,
            contentDescription = null,
            modifier = modifier,
            colorFilter = colorFilter
        )
    else
        Image(
            bitmap = image,
            contentDescription = null,
            modifier = modifier,
        )
}