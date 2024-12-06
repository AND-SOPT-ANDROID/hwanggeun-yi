package org.sopt.and.domain.repository

import org.sopt.and.domain.entity.SignInData

interface SignInRepository {
    suspend fun signIn(username: String, password: String): Result<SignInData>
}