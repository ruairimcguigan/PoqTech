package com.demo.poqtech.di

import com.demo.poqtech.viewmodel.ViewModelModule
import dagger.Module

@Module(includes = [
    ViewModelModule::class
])
abstract class AppModule {
//    @Binds
//    internal abstract fun bindContext(application: PoqApp): Application
}