package com.demo.poqtech.data.repo

import com.demo.poqtech.data.api.RepoService
import com.demo.poqtech.data.model.ReposResponse
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val service: RepoService
) : Repository {

    override fun getAllRepos(): Single<Response<ReposResponse>> = service.fetchReposDetails()

}