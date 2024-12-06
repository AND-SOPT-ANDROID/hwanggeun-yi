package org.sopt.and.core.data.repositoryimpl

import org.sopt.and.core.data.datasource.SignInDataSource
import org.sopt.and.core.data.dto.reqeust.LoginRequest
import org.sopt.and.core.data.service.HobbyService
import org.sopt.and.core.data.service.SignInService
import org.sopt.and.domain.repository.HobbyRepository
import org.sopt.and.domain.repository.SignInRepository

class SignInRepositoryImpl(private val signInDataSource: SignInDataSource) : SignInRepository {
    override suspend fun signIn(username: String, password: String): Result<String> = runCatching {
        val response = signInDataSource.postSignIn(LoginRequest(username, password))
        response.result.token
    }
}