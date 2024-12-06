package org.sopt.and.core.util

import android.content.Context
import retrofit2.HttpException
import org.sopt.and.R

interface ErrorHandler {
    fun handleNetworkError(exception: Throwable?): String
}

class DefaultErrorHandler(private val context: Context) : ErrorHandler {
    override fun handleNetworkError(exception: Throwable?): String {
        return when (exception) {
            is HttpException -> when (exception.code()) {
                400 -> context.getString(R.string.network_error_400)
                403 -> context.getString(R.string.network_error_403)
                409 -> context.getString(R.string.network_error_409)
                else -> context.getString(R.string.network_error)
            }
            else -> context.getString(R.string.network_error)
        }
    }
}