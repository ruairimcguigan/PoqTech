package com.demo.poqtech

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory
@Inject constructor(
    private val creators: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(viewModelClass: Class<T>): T {
        val creator: Provider<out ViewModel>? = creators[viewModelClass]

        if (creator != null) {
            return creator.get() as T
        } else {
            for ((key, value) in creators) {
                if (viewModelClass.isAssignableFrom(key)) {
                    return value.get() as T
                }
            }
            throw IllegalArgumentException("unknown ViewModel: $viewModelClass")
        }
    }
}