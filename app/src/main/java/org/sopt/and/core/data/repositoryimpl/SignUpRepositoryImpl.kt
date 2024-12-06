package org.sopt.and.core.data.repositoryimpl

import org.sopt.and.core.data.datasource.SignUpDataSource
import org.sopt.and.core.data.dto.reqeust.CreateUserRequest
import org.sopt.and.core.data.dto.response.CreateUserResponse
import org.sopt.and.domain.entity.SignUpData
import org.sopt.and.domain.repository.SignUpRepository

class SignUpRepositoryImpl(private val signUpDataSource: SignUpDataSource) : SignUpRepository {
    override suspend fun signUp(username: String, password: String, hobby: String): Result<SignUpData> = runCatching {
        val response = signUpDataSource.postSignUp(CreateUserRequest(username, password, hobby))
        SignUpData(response.result.userId)
    }
}