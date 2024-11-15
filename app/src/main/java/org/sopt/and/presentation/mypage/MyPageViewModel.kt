package org.sopt.and.presentation.mypage

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.and.network.RetrofitInstance
import retrofit2.HttpException
import java.io.IOException

class MyPageViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MyPageUiState())
    val uiState = _uiState.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    fun fetchMyHobby(context: Context) {
        viewModelScope.launch {
            try {
                val token = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
                    .getString("token", "") ?: ""

                val response = RetrofitInstance.authService.getMyHobby(token)
                _uiState.value = MyPageUiState(hobby = response.result.hobby)
            } catch (e: HttpException) {
                when (e.code()) {
                    401 -> {
                        _errorMessage.value = "인증 토큰이 없습니다"
                        Log.e("MyPage", "Authentication error: Missing token", e)
                    }
                    403 -> {
                        _errorMessage.value = "유효하지 않은 토큰입니다"
                        Log.e("MyPage", "Authentication error: Invalid token", e)
                    }
                    404 -> {
                        _errorMessage.value = "유효하지 못한 요청입니다"
                        Log.e("MyPage", "Not found error", e)
                    }
                    else -> {
                        _errorMessage.value = "서버 오류가 발생했습니다"
                        Log.e("MyPage", "Server error: ${e.code()}", e)
                    }
                }
            } catch (e: IOException) {
                _errorMessage.value = "네트워크 연결을 확인해주세요"
                Log.e("MyPage", "Network error", e)
            } catch (e: Exception) {
                _errorMessage.value = "알 수 없는 오류가 발생했습니다"
                Log.e("MyPage", "Unexpected error", e)
            }
        }
    }
}

data class MyPageUiState(
    val hobby: String = "",
)