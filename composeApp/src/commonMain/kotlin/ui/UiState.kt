package ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import network.vo.AnonimousResult

data class UiState(
	var cookie: String,
)

class MusakiViewModel : ViewModel() {
	companion object {
		const val BASE_URL = "http://106.14.11.237:3000"
	}

	var uiState by mutableStateOf<UiState>(
		UiState(
			cookie = ""
		)
	)

	private val client = HttpClient(CIO) {
		install(ContentNegotiation) {
			json(Json {
				ignoreUnknownKeys = true
			})
		}
	}

	init {
		runBlocking { getCookie() }
	}

	private suspend fun getCookie() {
		val res = client.get("$BASE_URL/register/anonimous").body<AnonimousResult>()
		uiState.cookie = res.cookie
	}
}
