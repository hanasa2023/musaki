package features.home.components.bottombar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import features.home.components.utils.MusakiIconButton
import features.home.components.utils.MusakiIconButtonBig
import musaki.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import ui.theme.IconSpacer
import ui.theme.IconSpacerBig

@Composable
fun MusakiBottomBar() {
    Row(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.inversePrimary)
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Left component
        Row(
            modifier = Modifier.weight(.3f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(Res.drawable.knd),
                contentDescription = "avatar",
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(56.dp)
                    .clip(CircleShape)
            )

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row {
                    Text(
                        text = "残机 - ",
                        fontSize = 16.sp
                    )
                    Text(
                        text = "ZUTOMAYO",
                        fontSize = 12.sp
                    )
                }

                Row(
                    modifier = Modifier
                        .height(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MusakiIconButton(
                        resource = Res.drawable.favorite,
                        contentDescription = null
                    )

                    IconSpacer()

                    MusakiIconButton(
                        resource = Res.drawable.download,
                        contentDescription = null
                    )

                    IconSpacer()

                    MusakiIconButton(
                        resource = Res.drawable.more,
                        contentDescription = null
                    )
                }
            }
        }

        //Center component
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(.4f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                MusakiIconButtonBig(
                    resource = Res.drawable.repeat,
                    contentDescription = null
                )

                IconSpacerBig()

                MusakiIconButtonBig(
                    resource = Res.drawable.skip_previous,
                    contentDescription = null
                )

                IconSpacerBig()

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Red, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.pause),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }

                IconSpacerBig()

                MusakiIconButtonBig(
                    resource = Res.drawable.skip_next,
                    contentDescription = null
                )

                IconSpacerBig()

                MusakiIconButtonBig(
                    resource = Res.drawable.lyrics,
                    contentDescription = null
                )
            }

            LinearProgressIndicator(
                progress = .5f,
                modifier = Modifier.fillMaxWidth()
            )
        }

        //Right component
        Row(
            modifier = Modifier.weight(.3f),
            horizontalArrangement = Arrangement.End
        ) {
            MusakiIconButtonBig(
                resource = Res.drawable.volume_up,
                contentDescription = null
            )

            IconSpacerBig()

            MusakiIconButtonBig(
                resource = Res.drawable.menu,
                contentDescription = null
            )
        }
    }
}