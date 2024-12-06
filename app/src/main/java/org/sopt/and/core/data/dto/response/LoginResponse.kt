package org.sopt.and.core.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LoginResponse(
    @SerialName("result")
    val result: Result
) {
    @Serializable
    data class Result(
        @SerialName("token")
        val token: String
    )
}
