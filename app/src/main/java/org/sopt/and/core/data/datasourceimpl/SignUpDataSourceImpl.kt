package org.sopt.and.core.data.datasourceimpl

import org.sopt.and.core.data.datasource.SignUpDataSource
import org.sopt.and.core.data.dto.BaseResponse
import org.sopt.and.core.data.dto.reqeust.CreateUserRequest
import org.sopt.and.core.data.dto.response.CreateUserResponse
import org.sopt.and.core.data.service.SignInService
import org.sopt.and.core.data.service.SignUpService

class SignUpDataSourceImpl(
    private val signUpService: SignUpService
) : SignUpDataSource {
    override suspend fun postSignUp(request: CreateUserRequest): BaseResponse<CreateUserResponse> = signUpService.signUp(request)
}