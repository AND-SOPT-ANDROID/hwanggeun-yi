package org.sopt.and.core.data.service

import org.sopt.and.core.data.dto.BaseResponse
import org.sopt.and.core.data.dto.reqeust.LoginRequest
import org.sopt.and.core.data.dto.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {
    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): BaseResponse<LoginResponse>
}