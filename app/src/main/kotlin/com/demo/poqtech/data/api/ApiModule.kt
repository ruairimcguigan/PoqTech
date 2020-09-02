package com.demo.poqtech.data.api

import android.app.Application
import android.content.Context
import com.demo.poqtech.BuildConfig.BASE_URL
import com.demo.poqtech.data.connectivity.DefaultNetworkState
import com.demo.poqtech.data.connectivity.NetworkState
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
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
    fun provideNetworkStateCheck(): NetworkState = DefaultNetworkState()
}