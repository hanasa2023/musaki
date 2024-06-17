package network.model

import kotlinx.serialization.Serializable

@Serializable
data class Creator(
	val defaultAvatar: Boolean,
	val province: Int,
	val authStatus: Int,
	val followed: Boolean,
	val gender: Int,
	val city: Int,
	val birthday: Long,
	val userId: Int,
	val userType: Int,
	val nickname: String,
	val signature: String,
	val description: String,
	val detailDescription: String,
	val avatarImgId: Long,
	val backgroundImgId: Long,
	val backgroundUrl: String,
	val authority: Int,
	val mutual: Boolean,
	val expertTags: List<String>,
	val experts: String?,
	val djStatus: Int,
	val vipType: Int,
	val remarkName: String?,
	val authenticationTypes: Int,
	val avatarDetail: AvatarDetail,
	val anchor: Boolean,
	val avatarImgIdStr: String,
	val backgroundImgIdStr: String
)
