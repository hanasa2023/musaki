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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate

actual class LoginViewModel : ViewModel() {
    actual val account = MutableStateFlow("")
    actual val password = MutableStateFlow("")
    actual val qRCodeResource = MutableStateFlow("")
    private val _isScanner = MutableStateFlow(false)
    private val _qrKey = MutableStateFlow("")
    private val _cookie = MutableStateFlow("")

    actual val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    //TODO:这里有一个bug，当第一次打开扫码登录时协程会报错
    init {
        Timer().scheduleAtFixedRate(0, 120_000) {
            runBlocking {
                getQRCode()
                checkLogin()
            }
        }
    }

    actual fun updateAccount(newAccount: String) {
        account.value = newAccount
    }

    actual fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    actual suspend fun getQRCode() {
        try {
            client.get("$BASE_URL/login/qr/key").body<QRKey>().data?.uniKey?.let { _qrKey.value = it }
            println(_qrKey)
            val timeStamp = System.currentTimeMillis()
            val qrCode =
                client.get("$BASE_URL/login/qr/create?key=$_qrKey&qrimg=true&timestamp=${timeStamp}")
                    .body<QRCode>()
            qrCode.data?.let { qRCodeResource.value = it.qrImg.split(",")[1] }
        } catch (_: Exception) {
        }
    }

    fun onScanner() {
        _isScanner.value = false
    }

    private suspend fun checkLogin() {
        try {
            val timeStamp = System.currentTimeMillis()
            val res = client.get("$BASE_URL/login/qr/check?key=$_qrKey&timestamp=$timeStamp").body<LoginCheck>()
            res.cookie?.let {
                if (it.isNotEmpty()) {
                    _isScanner.value = true
                    _cookie.value = it
                    println(_cookie)
                }
            }
        } catch (_: Exception) {
        }
    }
}
