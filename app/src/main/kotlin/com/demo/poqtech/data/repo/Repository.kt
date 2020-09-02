package com.demo.poqtech.data.repo

import com.demo.poqtech.data.model.ReposResponse
import io.reactivex.Single
import retrofit2.Response

interface Repository {
    fun getAllRepos(): Single<Response<ReposResponse>>
}
