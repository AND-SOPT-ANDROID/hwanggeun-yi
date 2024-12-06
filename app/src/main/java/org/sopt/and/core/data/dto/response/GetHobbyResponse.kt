package org.sopt.and.core.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetHobbyResponse(
    @SerialName("result")
    val result: Result
) {
    @Serializable
    data class Result(
        @SerialName("hobby")
        val hobby: String
    )
}
