package org.sopt.and.presentation.auth

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.core.util.DefaultErrorHandler
import org.sopt.and.domain.entity.SignInData
import org.sopt.and.domain.repository.RepositoryPool
import org.sopt.and.utils.KeyStorage
import retrofit2.HttpException

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    private val errorHandler = DefaultErrorHandler(context)

    private val _username = MutableLiveData("")
    val username: LiveData<String> get() = _username

    private val _password = MutableLiveData("")
    val password: LiveData<String> get() = _password

    private val _hobby = MutableLiveData("")
    val hobby: LiveData<String> get() = _hobby

    val signInSuccess = MutableLiveData(false)
    val signUpSuccess = MutableLiveData(false)
    val errorMessage = MutableLiveData("")

    private val signInRepository = RepositoryPool.signInRepository
    private val signUpRepository = RepositoryPool.signUpRepository

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
            val result = signInRepository.signIn(_username.value.orEmpty(), _password.value.orEmpty())

            result.onSuccess {  signInData ->
                context.getSharedPreferences("auth", Context.MODE_PRIVATE).edit()
                    .putString("token", signInData.token.orEmpty())
                    .apply()

                signInSuccess.value = true
                errorMessage.value = context.getString(R.string.signin_success)
                onSignInSuccess()
            }.onFailure{
                handleSignInError(result.exceptionOrNull())
            }
        }
    }

    fun onSignUpClick(onSignUpSuccess: () -> Unit) {
        viewModelScope.launch {
            if (!isValidInput()) {
                errorMessage.value = context.getString(R.string.sign_up_error_8_over)
                return@launch
            }

            if (!isValidPassword(_password.value ?: "")) {
                errorMessage.value = context.getString(R.string.sign_up_error_password_form)
                return@launch
            }

            val result = signUpRepository.signUp(
                _username.value.orEmpty(),
                _password.value.orEmpty(),
                _hobby.value.orEmpty()
            )

            if (result.isSuccess) {
                signUpSuccess.value = true
                errorMessage.value = context.getString(R.string.signup_success)
                onSignUpSuccess()
            } else {
                handleSignUpError(result.exceptionOrNull())
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

    private fun handleSignUpError(exception: Throwable?) {
        errorMessage.value = errorHandler.handleNetworkError(exception)
        signUpSuccess.value = false
    }

    private fun handleSignInError(exception: Throwable?) {
        errorMessage.value = errorHandler.handleNetworkError(exception)
        signInSuccess.value = false
    }
}

