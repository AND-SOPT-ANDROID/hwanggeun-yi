package org.sopt.and.core.data.datasourceimpl

import org.sopt.and.core.data.datasource.HobbyDataSource
import org.sopt.and.core.data.dto.BaseResponse
import org.sopt.and.core.data.dto.response.GetHobbyResponse
import org.sopt.and.core.data.service.HobbyService

class HobbyDataSourceImpl(
    private val hobbyService: HobbyService
) : HobbyDataSource {
    override suspend fun getHobby(token: String): BaseResponse<GetHobbyResponse> = hobbyService.getMyHobby(token)
}