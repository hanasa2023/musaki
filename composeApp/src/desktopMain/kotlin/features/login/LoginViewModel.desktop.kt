package features.login

import androidx.lifecycle.ViewModel
import features.login.models.LoginCheck
import features.login.models.QRCode
import features.login.models.QRKey
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.json.Json
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate

actual class LoginViewModel : ViewModel() {
    actual val phone = MutableStateFlow("")
    actual val password = MutableStateFlow("")
    actual val qRCodeResource = MutableStateFlow("")
    actual val loginCode = MutableStateFlow(0)
    private val _qrKey = MutableStateFlow("")
    private val _cookie = MutableStateFlow("")
    private lateinit var _timer: Timer
    private lateinit var _scope: CoroutineScope

    actual val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    actual fun updateAccount(newAccount: String) {
        phone.value = newAccount
    }

    actual fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    suspend fun startCodeTimer() {
        _timer = Timer()
        _scope = CoroutineScope(Dispatchers.IO + Job())
        _scope.launch { async { refreshQRCode() }.await() }
        _timer.scheduleAtFixedRate(0, 1000) {
            _scope.launch {
                checkQRLogin()
            }
        }
    }

    fun stopTimer() {
        _timer.cancel()
        _scope.cancel()
    }

    actual suspend fun refreshQRCode() {
        try {
            client.get("$BASE_URL/login/qr/key").body<QRKey>().data?.uniKey?.let { _qrKey.value = it }
            println(_qrKey.value)
            val timeStamp = System.currentTimeMillis()
            val qrCode =
                client.get("$BASE_URL/login/qr/create?key=${_qrKey.value}&qrimg=true&timestamp=${timeStamp}")
                    .body<QRCode>()
            qrCode.data?.let { qRCodeResource.value = it.qrImg.split(",")[1] }
            println(qRCodeResource.value)
        } catch (_: Exception) {
        }
    }

    private suspend fun checkQRLogin() {
        try {
            val timeStamp = System.currentTimeMillis()
            val res = client.get("$BASE_URL/login/qr/check?key=${_qrKey.value}&timestamp=$timeStamp").body<LoginCheck>()
            loginCode.value = res.code
            when (res.code) {
                800 -> {
//                    println("800")
                }

                801 -> {
//                    println("801")
                }

                802 -> {}
                803 -> {
                    res.cookie?.let {
                        _cookie.value = it
                        println(_cookie.value)
                    }
                    stopTimer()
                }

                502 -> {}
            }
        } catch (_: Exception) {
        }
    }

    suspend fun onLogin() {
        val timeStamp = System.currentTimeMillis()
        client.get("$BASE_URL/login/cellphone?phone=${phone.value}&password=${password.value}&timestamp=$timeStamp")
    }
}
