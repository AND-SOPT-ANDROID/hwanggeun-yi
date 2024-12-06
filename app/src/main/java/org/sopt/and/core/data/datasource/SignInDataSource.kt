package org.sopt.and.core.data.datasource

import org.sopt.and.core.data.dto.BaseResponse
import org.sopt.and.core.data.dto.reqeust.LoginRequest
import org.sopt.and.core.data.dto.response.LoginResponse

interface SignInDataSource {
    suspend fun postSignIn(
        request: LoginRequest
    ): BaseResponse<LoginResponse>
}