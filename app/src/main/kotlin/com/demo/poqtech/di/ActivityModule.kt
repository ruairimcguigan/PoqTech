package com.demo.poqtech.di

import com.demo.poqtech.allrepos.AllReposActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): AllReposActivity
}