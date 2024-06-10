package features.home.components.sidebar

import androidx.compose.foundation.Image
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import features.pages.utils.*
import musaki.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ui.theme.IconSpacer

@Composable
actual fun MusakiSideBar(
	navController: NavController,
) {
	val lazyListState = rememberLazyListState()

	Row(
		modifier = Modifier
			.fillMaxWidth()
			.height(60.dp)
			.padding(horizontal = 16.dp),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Start
	) {
		Image(
			painter = painterResource(Res.drawable.musaki),
			contentDescription = "logo",
			modifier = Modifier.size(40.dp)
		)

		IconSpacer()

		Text(
			text = stringResource(Res.string.app_name),
			fontSize = 24.sp
		)
	}

	Box {
		LazyColumn(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 16.dp),
			state = lazyListState
		) {
			item {
				MusakiSideBarListItem(
					resource = Res.drawable.home,
					title = "主页",
					onClick = { navController.moveToScreen(HOME_ROUTE) }
				)
			}

			item {
				MusakiSideBarListItem(
					resource = Res.drawable.grid_view,
					title = "浏览",
					onClick = { navController.moveToScreen(EXPLORE_ROUTE) }
				)
			}

			item {
				MusakiSideBarListItem(
					resource = Res.drawable.radio,
					title = "广播",
					onClick = { navController.moveToScreen(RADIO_ROUTE) }
				)
			}

			item {
				Text(
					text = "资料库",
					fontSize = 10.sp,
				)
			}

			item {
				MusakiSideBarListItem(
					resource = Res.drawable.schedule,
					title = "最近添加",
					onClick = { navController.moveToScreen(RECENT_ADD_ROUTE) }
				)
			}

			item {
				MusakiSideBarListItem(
					resource = Res.drawable.mic,
					title = "艺人",
					onClick = { navController.moveToScreen(ARTISTS_ROUTE) }
				)
			}

			item {
				MusakiSideBarListItem(
					resource = Res.drawable.album,
					title = "专辑",
					onClick = { navController.moveToScreen(ALBUMS_ROUTE) }
				)
			}

			item {
				MusakiSideBarListItem(
					resource = Res.drawable.music_note,
					title = "歌曲",
					onClick = { navController.moveToScreen(SONGS_ROUTE) }
				)
			}

			item {
				MusakiSideBarListItem(
					resource = Res.drawable.account_box,
					title = "专属推荐",
					onClick = { navController.moveToScreen(RECOMMENDATIONS_ROUTE) }
				)
			}

			item {
				Text(
					text = "播放列表",
					fontSize = 10.sp,
				)
			}

			items(musakiSideBarList.size) { index ->
				MusakiSideBarListItem(
					resource = musakiSideBarList[index].resource,
					title = musakiSideBarList[index].title
				)
			}
		}
		VerticalScrollbar(
			modifier = Modifier
				.align(Alignment.CenterEnd)
				.fillMaxHeight(),
			adapter = rememberScrollbarAdapter(lazyListState)
		)
	}
}
