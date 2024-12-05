package org.sopt.and.network

import org.sopt.and.core.data.dto.reqeust.CreateUserRequest
import org.sopt.and.core.data.dto.reqeust.LoginRequest
import org.sopt.and.core.data.dto.response.CreateUserResponse
import org.sopt.and.core.data.dto.response.GetHobbyResponse
import org.sopt.and.core.data.dto.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("user")
    suspend fun signUp(
        @Body request: CreateUserRequest
    ): CreateUserResponse

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @GET("user/my-hobby")
    suspend fun getMyHobby(
        @Header("token") token: String
    ): GetHobbyResponse
}