package network.vo

import kotlinx.serialization.Serializable
import network.model.PlayList

@Serializable
data class PlayListResults(
	val playlists: List<PlayList>
)