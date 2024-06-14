package features.login.models

import kotlinx.serialization.Serializable

@Serializable
data class PhoneLoginData(
    val loginType: Int? = null,
    val code: Int,
    val token: String? = null,
    val cookie: String? = null
)
