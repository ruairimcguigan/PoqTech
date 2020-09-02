package com.demo.poqtech.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.poqtech.allrepos.AllReposViewModel
import com.demo.poqtech.viewmodel.ViewModelFactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AllReposViewModel::class)
    abstract fun bindReposViewModel(weatherViewModelAll: AllReposViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}