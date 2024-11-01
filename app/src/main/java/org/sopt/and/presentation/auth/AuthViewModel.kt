package org.sopt.and.presentation.auth

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.utils.KeyStorage
import java.util.regex.Pattern.matches

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    private val _email = MutableLiveData("")
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> get() = _password

    val signInSuccess = MutableLiveData(false)
    val signUpSuccess = MutableLiveData(false)
    val errorMessage = MutableLiveData("")

    private val _savedEmail = MutableLiveData<String?>()
    private val _savedPassword = MutableLiveData<String?>()

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun onSignInClick(onSignInSuccess: (String, String) -> Unit) {
        viewModelScope.launch {
            val emailValue = _email.value
            val passwordValue = _password.value
            Log.d("Tag", _savedEmail.value + _savedPassword.value + " : " + emailValue + passwordValue )

            if (emailValue == _savedEmail.value && passwordValue == _savedPassword.value) {
                signInSuccess.value = true
                errorMessage.value = context.getString(R.string.signin_success)
                delay(1L)
                onSignInSuccess(emailValue ?: "", passwordValue ?: "")
            } else {
                errorMessage.value = context.getString(R.string.signin_fail)
                signInSuccess.value = false
            }
        }
    }

    fun onSignUpClick(onSignUpSuccess: () -> Unit) {
        viewModelScope.launch {
            val emailValue = _email.value
            val passwordValue = _password.value

            if (isValidEmail(emailValue ?: "") && isValidPassword(passwordValue ?: "")) {
                _savedEmail.value = emailValue
                _savedPassword.value = passwordValue
                signUpSuccess.value = true
                errorMessage.value = context.getString(R.string.signup_success)
                delay(1L)
                onSignUpSuccess()
            } else {
                errorMessage.value = context.getString(R.string.sign_up_failure)
                signUpSuccess.value = false
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        return Regex(KeyStorage.EMAIL_REGEX).matches(password)
    }
}

