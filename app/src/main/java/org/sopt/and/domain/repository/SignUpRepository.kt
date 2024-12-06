package org.sopt.and.domain.repository

import org.sopt.and.core.data.dto.response.CreateUserResponse
import org.sopt.and.domain.entity.SignUpData

interface SignUpRepository {
    suspend fun signUp(username: String, password: String, hobby: String): Result<SignUpData>
}