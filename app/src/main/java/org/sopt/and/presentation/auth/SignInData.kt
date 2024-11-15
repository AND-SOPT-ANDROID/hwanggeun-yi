package org.sopt.and.presentation.auth

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val result: LoginResult,
    val code: String? = null
)

data class LoginResult(
    val token: String
)

