package network.vo

import kotlinx.serialization.Serializable

@Serializable
data class PhoneLoginResult(
    val loginType: Int? = null,
    val code: Int,
    val token: String? = null,
    val cookie: String? = null
)
