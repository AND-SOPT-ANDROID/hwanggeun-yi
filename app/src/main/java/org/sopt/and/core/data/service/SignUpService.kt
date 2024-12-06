package org.sopt.and.core.data.service

import org.sopt.and.core.data.dto.BaseResponse
import org.sopt.and.core.data.dto.reqeust.CreateUserRequest
import org.sopt.and.core.data.dto.response.CreateUserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("user")
    suspend fun signUp(
        @Body request: CreateUserRequest
    ): BaseResponse<CreateUserResponse>
}