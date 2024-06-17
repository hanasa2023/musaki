package ui.login

import androidx.lifecycle.ViewModel
import io.ktor.client.*
import kotlinx.coroutines.flow.MutableStateFlow

actual class LoginViewModel : ViewModel() {
    actual val loginCode: MutableStateFlow<Int>
        get() = TODO("Not yet implemented")
    actual val qRCodeResource: MutableStateFlow<String>
        get() = TODO("Not yet implemented")
    actual val client: HttpClient
        get() = TODO("Not yet implemented")

    actual suspend fun refreshQRCode() {
    }

}