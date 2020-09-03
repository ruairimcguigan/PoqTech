package com.demo.poqtech.data.api

import android.app.Application
import com.demo.poqtech.PoqApp
import com.demo.poqtech.data.connectivity.DefaultNetworkState
import com.demo.poqtech.data.connectivity.NetworkState
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(app: Application, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl((app as PoqApp).getBaseUrl())
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    fun provideReposApi(retrofit: Retrofit): ReposApi = retrofit.create(
        ReposApi::class.java)

    @Provides
    fun provideReposService(repoApi: ReposApi): RepoService =
        RepoService(repoApi)

    @Provides
    fun provideNetworkStateCheck(app: Application): NetworkState = DefaultNetworkState(app)

}