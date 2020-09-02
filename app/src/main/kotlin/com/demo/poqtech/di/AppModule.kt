package com.demo.poqtech.di

import android.app.Application
import com.demo.poqtech.PoqApp
import com.demo.poqtech.ViewModelModule
import dagger.Binds
import dagger.Module

@Module(includes = [
    ViewModelModule::class
])
abstract class AppModule {
    @Binds
    internal abstract fun bindContext(application: PoqApp): Application
}