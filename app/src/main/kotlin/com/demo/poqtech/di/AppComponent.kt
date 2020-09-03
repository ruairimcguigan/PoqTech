package com.demo.poqtech.di

import android.app.Application
import com.demo.poqtech.PoqApp
import com.demo.poqtech.allrepos.AllReposModule
import com.demo.poqtech.data.api.ApiModule
import com.demo.poqtech.data.repo.RepoModule
import com.demo.poqtech.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        ApiModule::class,
        RepoModule::class,
        AllReposModule::class
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