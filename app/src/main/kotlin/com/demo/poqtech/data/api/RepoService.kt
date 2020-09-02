package com.demo.poqtech.data.api

import com.demo.poqtech.data.model.ReposResponse
import io.reactivex.Single
import retrofit2.Response

class RepoService(private val reposApi: ReposApi) {

    fun fetchReposDetails(): Single<Response<ReposResponse>> = reposApi.fetchAllRepos()
}
