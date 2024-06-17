package network.vo

import kotlinx.serialization.Serializable
import network.model.QRCode

@Serializable
data class QRCodeResult(
    val code: Int,
    val data: QRCode? = null
)