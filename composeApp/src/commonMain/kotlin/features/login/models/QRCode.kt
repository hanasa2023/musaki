package features.login.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QRCode(
    val code: Int,
    val data: QRCodeData? = null
)

@Serializable
data class QRCodeData(
    @SerialName("qrurl")
    val qrUrl: String,
    @SerialName("qrimg")
    val qrImg: String
)
