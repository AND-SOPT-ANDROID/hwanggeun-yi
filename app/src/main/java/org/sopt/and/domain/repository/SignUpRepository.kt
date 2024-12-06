package org.sopt.and.domain.repository

import org.sopt.and.core.data.dto.reqeust.CreateUserRequest
import org.sopt.and.core.data.service.SignUpService
import org.sopt.and.core.data.service.ServicePool

interface SignUpRepository {
    suspend fun signUp(username: String, password: String, hobby: String): Result<Unit>
}