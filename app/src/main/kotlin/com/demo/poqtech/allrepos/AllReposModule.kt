package com.demo.poqtech.allrepos

import dagger.Module
import dagger.Provides

@Module
class AllReposModule {

    @Provides fun providesAllReposAdapter() = AllReposAdapter()
}