package org.sopt.and.core.data.datasource

import org.sopt.and.core.data.dto.BaseResponse
import org.sopt.and.core.data.dto.response.GetHobbyResponse

interface HobbyDataSource {
    suspend fun getHobby(
        token: String
    ): BaseResponse<GetHobbyResponse>
}