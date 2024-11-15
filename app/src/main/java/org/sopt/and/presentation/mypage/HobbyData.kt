package org.sopt.and.presentation.mypage

data class HobbyResponse(
    val result: HobbyResult,
    val code: String? = null
)

data class HobbyResult(
    val hobby: String
)
