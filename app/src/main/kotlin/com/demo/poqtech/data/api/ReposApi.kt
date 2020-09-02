package com.demo.poqtech.data.api

import com.demo.poqtech.data.model.ReposResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface ReposApi {

    @GET("repos")
    fun fetchAllRepos() : Single<Response<ReposResponse>>
}