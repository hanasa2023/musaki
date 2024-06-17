package network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QRKey(
	val code: Int,
	@SerialName("unikey")
	val uniKey: String
)
