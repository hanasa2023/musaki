package network.database

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import network.vo.PlayListResults

object NetworkModule {
	private const val BASE_URL = "http://106.14.11.237:3000"

	private val client = HttpClient(CIO) {
		install(ContentNegotiation) {
			json(Json {
				ignoreUnknownKeys = true
			})
		}
	}

	suspend fun getPlaylist(): PlayListResults  {
		return client.get("$BASE_URL/top/playlist?limit=10&order=hot").body<PlayListResults>()
	}
}