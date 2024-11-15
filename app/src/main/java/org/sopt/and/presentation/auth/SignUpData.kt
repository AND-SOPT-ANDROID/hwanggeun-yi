package org.sopt.and.presentation.auth

data class SignUpRequest(
    val username: String,
    val password: String,
    val hobby: String
)

data class SignUpResponse(
    val result: SignUpResult,
    val code: String? = null
)

data class SignUpResult(
    val no: Int
)
