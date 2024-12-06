package org.sopt.and.core.data.repositoryimpl

import org.sopt.and.core.data.datasource.SignUpDataSource
import org.sopt.and.core.data.dto.reqeust.CreateUserRequest
import org.sopt.and.core.data.service.HobbyService
import org.sopt.and.core.data.service.SignUpService
import org.sopt.and.domain.repository.HobbyRepository
import org.sopt.and.domain.repository.SignUpRepository

class SignUpRepositoryImpl(private val signUpDataSource: SignUpDataSource) : SignUpRepository {
    override suspend fun signUp(username: String, password: String, hobby: String): Result<Unit> = runCatching {
        signUpDataSource.postSignUp(CreateUserRequest(username, password, hobby))
    }
}