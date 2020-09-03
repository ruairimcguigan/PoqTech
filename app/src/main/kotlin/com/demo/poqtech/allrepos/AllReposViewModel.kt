package com.demo.poqtech.allrepos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.poqtech.data.api.ApiResponse
import com.demo.poqtech.data.api.ApiResponse.Companion.BAD_GATEWAY
import com.demo.poqtech.data.api.ApiResponse.Companion.FORBIDDEN
import com.demo.poqtech.data.api.ApiResponse.Companion.FOUND_REDIRECT
import com.demo.poqtech.data.api.ApiResponse.Companion.INTERNAL_ERROR
import com.demo.poqtech.data.api.ApiResponse.Companion.MOVED
import com.demo.poqtech.data.api.ApiResponse.Companion.NOT_FOUND
import com.demo.poqtech.data.api.ApiResponse.Error
import com.demo.poqtech.data.api.ApiResponse.HttpErrors.*
import com.demo.poqtech.data.api.ApiResponse.Loading
import com.demo.poqtech.data.connectivity.NetworkState
import com.demo.poqtech.data.model.ReposResponse
import com.demo.poqtech.data.repo.Repository
import com.demo.poqtech.rx.RxDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class AllReposViewModel @Inject constructor(
    private val repo: Repository,
    private val disposable: RxDisposable,
    private val networkState: NetworkState
) : ViewModel() {

    val activeNetworkState: MutableLiveData<Boolean> = MutableLiveData()
    private val reposResult: MutableLiveData<ApiResponse> = MutableLiveData()

    fun fetchRepos(): MutableLiveData<ApiResponse> {
        if (networkState.isAvailable()) {
            disposable.add(
                repo.getAllRepos()
                    .doOnSubscribe { reposResult.postValue(Loading) }
                    .subscribeOn(Schedulers.io())
                    .subscribeWith(object : DisposableSingleObserver<Response<ReposResponse>>() {

                        override fun onSuccess(response: Response<ReposResponse>) =
                            if (response.isSuccessful) onFetchSuccess(response)
                            else handleError(response)

                        override fun onError(e: Throwable) = handleThrowable(e)
                    })
            )
        } else {
            activeNetworkState.value = false
        }
        return reposResult
    }

    private fun onFetchSuccess(response: Response<ReposResponse>)
            = reposResult.postValue(ApiResponse.Success(response.body()))

    private fun handleError(response: Response<ReposResponse>) = when (response.code()) {
        FORBIDDEN -> reposResult.postValue(Forbidden(FORBIDDEN.toString()))
        NOT_FOUND -> reposResult.postValue(NotFound(response.message()))
        INTERNAL_ERROR -> reposResult.postValue(InternalError(response.message()))
        BAD_GATEWAY -> reposResult.postValue(BadGateway(response.message()))
        MOVED -> reposResult.postValue(ResourceMoved(response.message()))
        FOUND_REDIRECT -> reposResult.postValue(ResourceNotFound(response.message()))
        else -> reposResult.postValue(Error(response.message()))
    }

    private fun handleThrowable(e: Throwable) {
        reposResult.postValue(Error(error = e.localizedMessage))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}