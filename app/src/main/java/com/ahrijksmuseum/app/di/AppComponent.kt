package com.ahrijksmuseum.app.di

import android.content.Context
import com.ahrijksmuseum.data.di.NetworkModule
import com.ahrijksmuseum.data.di.RepositoryModule
import com.ahrijksmuseum.presentation.di.CoroutineModule
import com.ahrijksmuseum.presentation.di.PresentationViewModelModule
import com.ahrijksmuseum.view.di.ViewComponent
import com.ahrijksmuseum.view.di.ViewModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        NetworkModule::class,
        RepositoryModule::class,
        CoroutineModule::class,
        PresentationViewModelModule::class,
        ViewModule::class
    ]
)
@ApplicationScope
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun viewComponentFactory(): ViewComponent.Factory

}