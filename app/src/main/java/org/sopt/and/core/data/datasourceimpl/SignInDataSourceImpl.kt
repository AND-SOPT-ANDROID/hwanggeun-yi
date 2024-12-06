package org.sopt.and.core.data.datasourceimpl

import org.sopt.and.core.data.datasource.SignInDataSource
import org.sopt.and.core.data.dto.BaseResponse
import org.sopt.and.core.data.dto.reqeust.LoginRequest
import org.sopt.and.core.data.dto.response.LoginResponse


class SignInDataSourceImpl(
    private val signInService: SignInDataSource
) : SignInDataSource {
    override suspend fun postSignIn(request: LoginRequest): BaseResponse<LoginResponse> = signInService.postSignIn(request)
}