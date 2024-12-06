package org.sopt.and.domain.repository

import org.sopt.and.core.data.service.ServicePool

interface HobbyRepository {
    suspend fun getHobby(token: String): Result<String>
}