package org.sopt.and.core.data.service

import org.sopt.and.core.data.dto.BaseResponse
import org.sopt.and.core.data.dto.response.GetHobbyResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface HobbyService {
    @GET("user/my-hobby")
    suspend fun getMyHobby(
        @Header("token") token: String
    ): BaseResponse<GetHobbyResponse>
}