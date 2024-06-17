package network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayList(
	val name: String,
	val id: Int,
	val trackNumberUpdateTime: Long,
	val status: Int,
	val userId: Int,
	val createTime: Long,
	val updateTime: Long,
	val subscribedCount: Int,
	val trackCount: Int,
	val cloudTrackCount: Int,
	val coverImgUrl: String,
	val iconImgUrl: String?,
	val coverImgId: Long,
	val description: String,
	val tags: List<String>,
	val playCount: Int,
	val trackUpdateTime: Long,
	val specialType: Int,
	val totalDuration: Int,
	val creator: Creator,
	val tracks: String?,
	//val subscribers
	val subscribed: Boolean,
	val commentThreadId: String,
	val newImported: Boolean,
	val adType: Int,
	val highQuality: Boolean,
	val privacy: Int,
	val ordered: Boolean,
	val anonimous: Boolean,
	val coverStatus: Int,
	val recommendInfo: String?,
	val socialPlayListCover: String?,
	val coverText: String?,
	val relateResType: Int?,
	val relateResId: Int?,
	val tsSongCount: Int?,
	val shareCount: Int,
	@SerialName("coverImgId_str")
	val coverImgIdStr: String,
	val alg: String,
	val commentCount: Int
)
