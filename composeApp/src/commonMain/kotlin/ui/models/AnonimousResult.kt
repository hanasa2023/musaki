package ui.models

import kotlinx.serialization.Serializable


@Serializable
data class AnonimousResult(
	val code: Int,
	val userId: Int,
	val createTime: Long,
	val cookie: String
)