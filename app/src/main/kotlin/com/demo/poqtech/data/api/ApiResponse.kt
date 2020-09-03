package com.demo.poqtech.data.api

import com.demo.poqtech.data.model.ReposResponse

sealed class ApiResponse {

    data class Success(val data: ReposResponse?) : ApiResponse()
    object Loading : ApiResponse()
    data class Error(val error: String) : ApiResponse()

    sealed class HttpErrors : ApiResponse() {
        data class Forbidden(val exception: String) : HttpErrors()
        data class NotFound(val exception: String) : HttpErrors()
        data class InternalError(val exception: String) : HttpErrors()
        data class BadGateway(val exception: String) : HttpErrors()
        data class ResourceMoved(val exception: String) : HttpErrors()
        data class ResourceNotFound(val exception: String) : HttpErrors()
    }

    companion object{
        const val MOVED = 301
        const val FOUND_REDIRECT = 301
        const val FORBIDDEN = 403
        const val NOT_FOUND = 404
        const val INTERNAL_ERROR = 500
        const val BAD_GATEWAY = 502
    }
}