package org.sopt.and.core.data.repositoryimpl

import org.sopt.and.core.data.datasource.HobbyDataSource
import org.sopt.and.core.data.service.HobbyService
import org.sopt.and.domain.repository.HobbyRepository

class HobbyRepositoryImpl(private val hobbyDataSource: HobbyDataSource) : HobbyRepository {
    override suspend fun getHobby(token: String): Result<String> = runCatching {
        val response = hobbyDataSource.getHobby(token)
        response.result.hobby
    }
}