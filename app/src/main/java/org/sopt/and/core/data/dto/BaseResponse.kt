package org.sopt.and.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse <T> (
    @SerialName("result")
    val result: T
)
