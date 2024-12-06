package org.sopt.and.core.data.repositoryimpl

import org.sopt.and.core.data.datasource.HobbyDataSource
import org.sopt.and.core.data.dto.response.GetHobbyResponse
import org.sopt.and.domain.entity.HobbyData
import org.sopt.and.domain.entity.SignInData
import org.sopt.and.domain.repository.HobbyRepository

class HobbyRepositoryImpl(private val hobbyDataSource: HobbyDataSource) : HobbyRepository {
    override suspend fun getHobby(token: String): Result<HobbyData> = runCatching {
        val response = hobbyDataSource.getHobby(token)
        HobbyData(response.result.hobby)
    }
}