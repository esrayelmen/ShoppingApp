package com.esrayelmen.e_market.util

import retrofit2.Response

class ApiResponseHandler<T>(private val onResponseHandler: OnResponseListener<T>) {
    interface OnResponseListener<T> {
        suspend fun onSuccess(response: Response<T>)
        suspend fun onClientError(errorMessage: String?)
        suspend fun onServerError(errorMessage: String?)
    }

    suspend fun handleResponse(response: Response<T>) {
        when (response.code()) {
            in 200..299 -> onResponseHandler.onSuccess(response)
            in 400..499 -> onResponseHandler.onClientError(response.message())
            in 500..599 -> onResponseHandler.onServerError(response.message())
        }
    }


}


