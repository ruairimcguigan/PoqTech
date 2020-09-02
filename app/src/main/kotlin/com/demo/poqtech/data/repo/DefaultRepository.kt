package com.demo.poqtech.data.repo

import com.demo.poqtech.data.api.RepoService
import com.demo.poqtech.data.model.ReposResponse
import com.demo.poqtech.rx.SchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Response
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val service: RepoService,
    private val scheduler: SchedulerProvider
//    private val apiResponse: ApiResponse
) : Repository {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()


//    override fun getAllRepos() {
//        compositeDisposable.add(
//        service.fetchReposDetails()
//                .doOnSubscribe{apiResponse.handleLoading(null)}
//                .subscribeOn(scheduler.io())
//                .doOnError{failure -> apiResponse.handleException<RepoResponseItem>(failure)}
////                .subscribe {repos -> apiResponse.handleSuccess(repos) }
//            .subscribe({ repos -> apiResponse.handleSuccess(repos)
//            }, { throwable ->
//                apiResponse.handleException<RepoResponseItem>(throwable)
//            })
//        )
//    }

    override fun getAllRepos(): Single<Response<ReposResponse>> = service.fetchReposDetails()

}