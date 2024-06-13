package features.login

import androidx.lifecycle.ViewModel
import io.ktor.client.*
import kotlinx.coroutines.flow.MutableStateFlow

const val BASE_URL = "http://106.14.11.237:3000"

expect class LoginViewModel : ViewModel {
    val phone: MutableStateFlow<String>
    val password: MutableStateFlow<String>
    val loginCode: MutableStateFlow<Int>
    val qRCodeResource: MutableStateFlow<String>
    val client: HttpClient


    fun updateAccount(newAccount: String)

    fun updatePassword(newPassword: String)

    suspend fun refreshQRCode()
}
