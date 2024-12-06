package org.sopt.and.domain.repository

import org.sopt.and.core.data.dto.reqeust.LoginRequest
import org.sopt.and.core.data.service.ServicePool

interface SignInRepository {
    suspend fun signIn(username: String, password: String): Result<String>
}