package com.demo.poqtech.di

import android.app.Application
import com.demo.poqtech.PoqApp
import com.demo.poqtech.data.api.ApiModule
import com.demo.poqtech.data.repo.RepoModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        ApiModule::class,
        RepoModule::class
    ]
)
interface AppComponent : AndroidInjector<PoqApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}