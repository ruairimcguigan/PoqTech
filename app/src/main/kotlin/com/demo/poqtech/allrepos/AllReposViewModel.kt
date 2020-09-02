package com.demo.poqtech.allrepos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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
import com.demo.poqtech.data.connectivity.DefaultNetworkState
import com.demo.poqtech.data.connectivity.NetworkState
import com.demo.poqtech.data.model.ReposResponse
import com.demo.poqtech.data.repo.Repository
import com.demo.poqtech.rx.RxDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class AllReposViewModel @Inject constructor(
    application: Application,
    private val repo: Repository,
    private val disposable: RxDisposable,
    private val networkState: DefaultNetworkState
) : AndroidViewModel(application) {

    val reposResult: MutableLiveData<ApiResponse> = MutableLiveData()
    val activeNetworkState: MutableLiveData<Boolean> = MutableLiveData()

    init {
        if (networkState.hasActiveState(application)) {
            fetchRepos()
        } else {
            activeNetworkState.value = false
        }
    }

    private fun fetchRepos() {
        disposable.add(
            repo.getAllRepos()
                .doOnSubscribe { reposResult.postValue(Loading) }
                .subscribeOn(Schedulers.io())
                .subscribe{response -> if (response.isSuccessful) onFetchSuccess(response) else handleError(response)}
        )
    }

    private fun onFetchSuccess(response: Response<ReposResponse>) {
        reposResult.postValue(ApiResponse.Success(response.body()))
    }

    private fun handleError(response: Response<ReposResponse>) {
        when (response.code()) {
            FORBIDDEN -> reposResult.postValue(Forbidden(response.message()))
            NOT_FOUND -> reposResult.postValue(NotFound(response.message()))
            INTERNAL_ERROR -> reposResult.postValue(InternalError(response.message()))
            BAD_GATEWAY -> reposResult.postValue(BadGateway(response.message()))
            MOVED -> reposResult.postValue(Removed(response.message()))
            FOUND_REDIRECT -> reposResult.postValue(ResourceNotFound(response.message()))
            else -> reposResult.postValue(Error(response.message()))
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}