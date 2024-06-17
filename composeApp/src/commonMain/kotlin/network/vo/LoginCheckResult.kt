package network.vo

import kotlinx.serialization.Serializable

@Serializable
data class LoginCheckResult(
    val code: Int,
    val message: String,
    val cookie: String? = null
)