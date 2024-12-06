package org.sopt.and.core.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserResponse(
    @SerialName("no")
    val userId: Int
)