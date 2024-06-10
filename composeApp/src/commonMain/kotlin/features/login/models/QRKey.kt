package features.login.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QRKey(
    val data: QRKeyData? = null,
    val code: Int
)

@Serializable
data class QRKeyData(
    val code: Int,
    @SerialName("unikey")
    val uniKey: String
)
