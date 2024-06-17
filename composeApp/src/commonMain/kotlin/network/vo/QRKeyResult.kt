package network.vo

import kotlinx.serialization.Serializable
import network.model.QRKey

@Serializable
data class QRKeyResult(
	val data: QRKey? = null,
	val code: Int
)

