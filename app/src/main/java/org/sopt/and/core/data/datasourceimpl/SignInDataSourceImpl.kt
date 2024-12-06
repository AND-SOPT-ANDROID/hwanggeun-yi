package org.sopt.and.core.data.datasourceimpl

import org.sopt.and.core.data.datasource.SignInDataSource
import org.sopt.and.core.data.dto.BaseResponse
import org.sopt.and.core.data.dto.reqeust.LoginRequest
import org.sopt.and.core.data.dto.response.LoginResponse
import org.sopt.and.core.data.service.SignInService


class SignInDataSourceImpl(
    private val signInService: SignInService
) : SignInDataSource {
    override suspend fun postSignIn(request: LoginRequest): BaseResponse<LoginResponse> = signInService.login(request)
}