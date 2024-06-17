package network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QRCode(
	@SerialName("qrurl")
	val qrUrl: String,
	@SerialName("qrimg")
	val qrImg: String
)
