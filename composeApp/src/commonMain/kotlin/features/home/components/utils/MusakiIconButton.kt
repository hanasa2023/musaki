package features.home.components.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun MusakiIconButton(
	resource: DrawableResource,
	contentDescription: String?,
	onClick: () -> Unit = {}
) {
	Icon(
		painter = painterResource(resource),
		contentDescription = contentDescription,
		modifier = Modifier
			.size(24.dp)
			.clickable(
				indication = null,
				interactionSource = remember { MutableInteractionSource() }
			) { onClick() }
	)
}

@Composable
fun MusakiIconButton(
	resource: ImageVector,
	contentDescription: String?,
	onClick: () -> Unit = {}
) {
	Icon(
		imageVector = resource,
		contentDescription = contentDescription,
		modifier = Modifier
			.size(24.dp)
			.clickable(
				indication = null,
				interactionSource = remember { MutableInteractionSource() }
			) { onClick() }
	)
}
@Composable
fun MusakiIconButtonBig(
	resource: DrawableResource,
	contentDescription: String?,
	onClick: () -> Unit = {}
) {
	Icon(
		painter = painterResource(resource),
		contentDescription = contentDescription,
		modifier = Modifier
			.size(28.dp)
			.clickable(
				indication = null,
				interactionSource = remember { MutableInteractionSource() }
			) { onClick() }
	)
}

@Composable
fun MusakiIconButtonBig(
	resource: ImageVector,
	contentDescription: String?,
	onClick: () -> Unit = {}
) {
	Icon(
		imageVector = resource,
		contentDescription = contentDescription,
		modifier = Modifier
			.size(28.dp)
			.clickable(
				indication = null,
				interactionSource = remember { MutableInteractionSource() }
			) { onClick() }
	)
}
