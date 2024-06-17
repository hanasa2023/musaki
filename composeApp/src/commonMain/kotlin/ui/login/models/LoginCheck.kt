package ui.login.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginCheck(
    val code: Int,
    val message: String,
    val cookie: String? = null
)