package ui.login

import androidx.lifecycle.ViewModel
import io.ktor.client.*
import kotlinx.coroutines.flow.MutableStateFlow

const val BASE_URL = "http://106.14.11.237:3000"

expect class LoginViewModel : ViewModel {
    val loginCode: MutableStateFlow<Int>
    val qRCodeResource: MutableStateFlow<String>
    val client: HttpClient

    suspend fun refreshQRCode()
}
