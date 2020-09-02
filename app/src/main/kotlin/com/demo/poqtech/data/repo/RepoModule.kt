package com.demo.poqtech.data.repo

import com.demo.poqtech.data.api.RepoService
import com.demo.poqtech.rx.RxDisposable
import com.demo.poqtech.rx.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class RepoModule {

    @Provides fun provideRxScheduler() = SchedulerProvider()

    @Provides fun provideDisposable() = RxDisposable()

    @Provides fun providesRepository(reposService: RepoService): Repository = DefaultRepository(reposService)
}