package com.demo.poqtech.di

import android.app.Application
import com.demo.poqtech.PoqApp
import com.demo.poqtech.ViewModelModule
import com.demo.poqtech.data.connectivity.DefaultNetworkState
import com.demo.poqtech.data.connectivity.NetworkState
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [
    ViewModelModule::class
])
abstract class AppModule {
//    @Binds
//    internal abstract fun bindContext(application: PoqApp): Application
}