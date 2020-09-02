package com.demo.poqtech

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.poqtech.ViewModelFactory
import com.demo.poqtech.ViewModelKey
import com.demo.poqtech.allrepos.ReposViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ReposViewModel::class)
    abstract fun bindReposViewModel(weatherViewModel: ReposViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}