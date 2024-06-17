package ui.pages.utils

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import ui.pages.albumsPage.MusakiAlbumsPage
import ui.pages.artistsPage.MusakiArtistsPage
import ui.pages.explorePage.MusakiExplorePage
import ui.pages.homePage.MusakiHomePage
import ui.pages.radioPage.MusakiRadioPage
import ui.pages.recentAddPage.MusakiRecentAddPage
import ui.pages.recommendationsPage.MusakiRecommendationsPage
import ui.pages.songsPage.MusakiSongsPage

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
