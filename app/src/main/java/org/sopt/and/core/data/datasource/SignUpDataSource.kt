package org.sopt.and.core.data.datasource

import org.sopt.and.core.data.dto.BaseResponse
import org.sopt.and.core.data.dto.reqeust.CreateUserRequest
import org.sopt.and.core.data.dto.response.CreateUserResponse

interface SignUpDataSource {
    suspend fun postSignUp(
        request: CreateUserRequest
    ): BaseResponse<CreateUserResponse>
}