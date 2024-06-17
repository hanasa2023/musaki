package ui.login

import androidx.lifecycle.ViewModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.json.Json
import network.vo.LoginCheckResult
import network.vo.PhoneLoginResult
import network.vo.QRCodeResult
import network.vo.QRKeyResult
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate

actual class LoginViewModel : ViewModel() {
	actual val qRCodeResource = MutableStateFlow("")
	actual val loginCode = MutableStateFlow(0)
	val phoneLoginCode = MutableStateFlow(502)
	val autoLogin = MutableStateFlow(false)
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

	fun resetLoginCode() {
		loginCode.value = 0
	}

	fun stopTimer() {
		_timer.cancel()
		_scope.cancel()
	}

	actual suspend fun refreshQRCode() {
		try {
			client.get("$BASE_URL/login/qr/key").body<QRKeyResult>().data?.uniKey?.let { _qrKey.value = it }
			println(_qrKey.value)
			val timeStamp = System.currentTimeMillis()
			val qrCode =
				client.get("$BASE_URL/login/qr/create?key=${_qrKey.value}&qrimg=true&timestamp=${timeStamp}")
					.body<QRCodeResult>()
			qrCode.data?.let { qRCodeResource.value = it.qrImg.split(",")[1] }
			println(qRCodeResource.value)
		} catch (_: Exception) {
		}
	}

	private suspend fun checkQRLogin() {
		try {
			val timeStamp = System.currentTimeMillis()
			val res = client.get("$BASE_URL/login/qr/check?key=${_qrKey.value}&timestamp=$timeStamp").body<LoginCheckResult>()
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

	suspend fun onPwdLogin(phone: String, password: String) {
		val timeStamp = System.currentTimeMillis()
		val res = client.get(
			"$BASE_URL/login/cellphone?phone=$phone&password=$password&timestamp=$timeStamp"
		).body<PhoneLoginResult>()
		println(res)
		phoneLoginCode.value = res.code
		res.cookie?.let { _cookie.value = it }
	}

	suspend fun onVerLogin(phone: String, verificationCode: String) {
		val timeStamp = System.currentTimeMillis()
		val res = client.get("$BASE_URL/login/cellphone?phone=$phone&captcha=$verificationCode&timestamp=$timeStamp")
			.body<PhoneLoginResult>()
		println(res)
		phoneLoginCode.value = res.code
		res.cookie?.let { _cookie.value = it }
	}

	fun updateAutoLoginState(isAutoLogin: Boolean) {
		autoLogin.value = isAutoLogin
	}

	suspend fun sendVerificationCode(phoneNumber: String) {
		client.get("$BASE_URL/captcha/sent?phone=$phoneNumber")
	}
}
