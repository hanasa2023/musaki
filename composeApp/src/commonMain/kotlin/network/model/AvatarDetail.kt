package network.model

import kotlinx.serialization.Serializable

@Serializable
data class AvatarDetail(
	val userType: Int,
	val identityLevel: Int,
	val identityIconUrl: String
)