package org.sopt.and.domain.repository

import org.sopt.and.core.data.dto.response.GetHobbyResponse
import org.sopt.and.domain.entity.HobbyData

interface HobbyRepository {
    suspend fun getHobby(token: String): Result<HobbyData>
}