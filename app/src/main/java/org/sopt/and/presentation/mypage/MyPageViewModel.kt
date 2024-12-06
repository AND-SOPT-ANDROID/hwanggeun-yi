package org.sopt.and.presentation.mypage

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.and.core.util.DefaultErrorHandler
import org.sopt.and.domain.repository.RepositoryPool
import retrofit2.HttpException
import java.io.IOException

class MyPageViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(MyPageUiState())
    val uiState = _uiState.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    private val hobbyRepository = RepositoryPool.hobbyRepository

    fun fetchMyHobby(context: Context) {
        val errorHandler = DefaultErrorHandler(context)
        viewModelScope.launch {
            val token = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
                .getString("token", "") ?: ""

            val result = hobbyRepository.getHobby(token)

            result.onSuccess { hobbyData ->
                _uiState.value = MyPageUiState(hobby = hobbyData.hobby)
            }.onFailure { e ->
                _errorMessage.value = errorHandler.handleNetworkError(e)
            }
        }
    }
}

data class MyPageUiState(
    val hobby: String = "",
)