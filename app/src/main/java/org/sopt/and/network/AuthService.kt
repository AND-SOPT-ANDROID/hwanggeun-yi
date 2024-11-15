package org.sopt.and.network

import org.sopt.and.presentation.auth.LoginRequest
import org.sopt.and.presentation.auth.LoginResponse
import org.sopt.and.presentation.auth.SignUpRequest
import org.sopt.and.presentation.auth.SignUpResponse
import org.sopt.and.presentation.mypage.HobbyResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("user")
    suspend fun signUp(
        @Body request: SignUpRequest
    ): SignUpResponse

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @GET("user/my-hobby")
    suspend fun getMyHobby(
        @Header("token") token: String
    ): HobbyResponse
}