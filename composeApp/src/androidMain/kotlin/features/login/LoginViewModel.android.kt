package features.login

import androidx.lifecycle.ViewModel
import io.ktor.client.*
import kotlinx.coroutines.flow.MutableStateFlow

actual class LoginViewModel : ViewModel() {
    actual val phone: MutableStateFlow<String>
        get() = TODO("Not yet implemented")
    actual val password: MutableStateFlow<String>
        get() = TODO("Not yet implemented")
    actual val loginCode: MutableStateFlow<Int>
        get() = TODO("Not yet implemented")
    actual val qRCodeResource: MutableStateFlow<String>
        get() = TODO("Not yet implemented")
    actual val client: HttpClient
        get() = TODO("Not yet implemented")

    actual fun updateAccount(newAccount: String) {
    }

    actual fun updatePassword(newPassword: String) {
    }

    actual suspend fun refreshQRCode() {
    }
}