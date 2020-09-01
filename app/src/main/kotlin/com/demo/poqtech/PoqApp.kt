package com.demo.poqtech

import com.demo.poqtech.BuildConfig.DEBUG
import com.demo.poqtech.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

open class PoqApp : DaggerApplication(){

    override fun onCreate() {
        super.onCreate()
        if (DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    open fun getBaseUrl() = BuildConfig.BASE_URL

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
}