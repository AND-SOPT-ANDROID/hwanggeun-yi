package org.sopt.and.presentation.mypage

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyPageViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MyPageUiState())
    val uiState = _uiState.asStateFlow()

    fun setUserInfo(email: String) {
        _uiState.value = _uiState.value.copy(
            email = email
        )
    }
}

data class MyPageUiState(
    val email: String = "",
)