package org.sopt.and.presentation.auth

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.network.RetrofitInstance
import org.sopt.and.utils.KeyStorage
import retrofit2.HttpException
import java.util.regex.Pattern.matches

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    private val _username = MutableLiveData("")
    val username: LiveData<String> get() = _username

    private val _password = MutableLiveData("")
    val password: LiveData<String> get() = _password

    private val _hobby = MutableLiveData("")
    val hobby: LiveData<String> get() = _hobby

    val signInSuccess = MutableLiveData(false)
    val signUpSuccess = MutableLiveData(false)
    val errorMessage = MutableLiveData("")

    fun setUsername(username: String) {
        _username.value = username
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setHobby(hobby: String) {
        _hobby.value = hobby
    }

    fun onSignInClick(onSignInSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.authService.login(
                    LoginRequest(
                        username = _username.value ?: "",
                        password = _password.value ?: ""
                    )
                )
                context.getSharedPreferences("auth", Context.MODE_PRIVATE).edit()
                    .putString("token", response.result.token)
                    .apply()

                signInSuccess.value = true
                errorMessage.value = context.getString(R.string.signin_success)
                onSignInSuccess()
            } catch (e: HttpException) {
                when (e.code()) {
                    400 -> errorMessage.value = context.getString(R.string.network_error_400)
                    403 -> errorMessage.value = context.getString(R.string.network_error_403)
                    else -> errorMessage.value = context.getString(R.string.signin_fail)
                }
                signInSuccess.value = false
            } catch (e: Exception) {
                errorMessage.value = context.getString(R.string.network_error)
                signInSuccess.value = false
            }
        }
    }

    fun onSignUpClick(onSignUpSuccess: () -> Unit) {
        viewModelScope.launch {
            if (!isValidInput()) {
                errorMessage.value = context.getString(R.string.sign_up_error_8_over)
                return@launch
            }

            if(!isValidPassword(_password.value ?: "")){
                errorMessage.value = context.getString(R.string.sign_up_error_password_form)
                return@launch
            }
            try {
                val response = RetrofitInstance.authService.signUp(
                    SignUpRequest(
                        username = _username.value ?: "",
                        password = _password.value ?: "",
                        hobby = _hobby.value ?: ""
                    )
                )

                signUpSuccess.value = true
                errorMessage.value = context.getString(R.string.signup_success)
                onSignUpSuccess()
            } catch (e: HttpException) {
                when (e.code()) {
                    400 -> errorMessage.value = context.getString(R.string.network_error_400)
                    409 -> errorMessage.value = context.getString(R.string.network_error_409)
                    else -> errorMessage.value = context.getString(R.string.signup_fail)
                }
                signUpSuccess.value = false
            } catch (e: Exception) {
                errorMessage.value = context.getString(R.string.network_error)
                signUpSuccess.value = false
            }
        }
    }

    private fun isValidInput(): Boolean {
        return (_username.value?.length ?: 0) <= 8 &&
                (_password.value?.length ?: 0) <= 8 &&
                (_hobby.value?.length ?: 0) <= 8
    }

    private fun isValidPassword(password: String): Boolean {
        return Regex(KeyStorage.EMAIL_REGEX).matches(password)
    }
}

