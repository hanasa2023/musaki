package features.pages.utils

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import features.pages.albumsPage.MusakiAlbumsPage
import features.pages.artistsPage.MusakiArtistsPage
import features.pages.explorePage.MusakiExplorePage
import features.pages.homePage.MusakiHomePage
import features.pages.radioPage.MusakiRadioPage
import features.pages.recentAddPage.MusakiRecentAddPage
import features.pages.recommendationsPage.MusakiRecommendationsPage
import features.pages.songsPage.MusakiSongsPage

const val HOME_ROUTE = "home"
const val EXPLORE_ROUTE = "explore"
const val RADIO_ROUTE = "radio"
const val RECENT_ADD_ROUTE = "recent_add"
const val ARTISTS_ROUTE = "artists"
const val ALBUMS_ROUTE = "albums"
const val SONGS_ROUTE = "songs"
const val RECOMMENDATIONS_ROUTE = "recommendations"

fun NavGraphBuilder.homeScreen() {
	navigation(startDestination = "homePage", route = HOME_ROUTE) {
		composable("homePage") {
			MusakiHomePage()
		}
	}
}

fun NavGraphBuilder.exploreScreen() {
	navigation(startDestination = "explorePage", route = EXPLORE_ROUTE) {
		composable("explorePage") {
			MusakiExplorePage()
		}
	}
}

fun NavGraphBuilder.radioScreen() {
	navigation(startDestination = "radioPage", route = RADIO_ROUTE) {
		composable("radioPage") {
			MusakiRadioPage()
		}
	}
}

fun NavGraphBuilder.recentAddScreen() {
	navigation(startDestination = "recentAddPage", route = RECENT_ADD_ROUTE) {
		composable("recentAddPage") {
			MusakiRecentAddPage()
		}
	}
}

fun NavGraphBuilder.artistsScreen() {
	navigation(startDestination = "artistsPage", route = ARTISTS_ROUTE) {
		composable("artistsPage") {
			MusakiArtistsPage()
		}
	}
}

fun NavGraphBuilder.albumsScreen() {
	navigation(startDestination = "albumsPage", route = ALBUMS_ROUTE) {
		composable("albumsPage") {
			MusakiAlbumsPage()
		}
	}
}

fun NavGraphBuilder.songsScreen() {
	navigation(startDestination = "songsPage", route = SONGS_ROUTE) {
		composable("songsPage") {
			MusakiSongsPage()
		}
	}
}

fun NavGraphBuilder.recommendationsScreen() {
	navigation(startDestination = "recommendationsPage", route = RECOMMENDATIONS_ROUTE) {
		composable("recommendationsPage") {
			MusakiRecommendationsPage()
		}
	}
}

fun NavController.moveToScreen(route: String) {
	val nav = this
	navigate(route) {
		nav.graph.findStartDestination().route?.let {
			popUpTo(it) {
				saveState = true
			}
		}
		launchSingleTop = true
		restoreState = true
	}
}
