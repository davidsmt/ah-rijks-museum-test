package com.ahrijksmuseum.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahrijksmuseum.presentation.factory.ViewModelFactory
import com.ahrijksmuseum.presentation.viewmodels.ArtObjectDetailsViewModel
import com.ahrijksmuseum.presentation.viewmodels.ArtObjectsViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class PresentationViewModelModule {

    @Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
    )
    @Retention(AnnotationRetention.RUNTIME)
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)

    @Binds
    abstract fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ArtObjectDetailsViewModel::class)
    abstract fun bindArtObjectDetailsViewModel(viewModel: ArtObjectDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArtObjectsViewModel::class)
    abstract fun bindArtObjectsViewModel(viewModel: ArtObjectsViewModel): ViewModel

}