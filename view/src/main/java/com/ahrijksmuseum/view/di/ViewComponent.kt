package com.ahrijksmuseum.view.di

import com.ahrijksmuseum.view.screens.artobjectdetails.ArtObjectDetailsFragment
import com.ahrijksmuseum.view.screens.artobjects.ArtObjectsFragment
import dagger.Subcomponent

@Subcomponent
@FragmentScope
interface ViewComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ViewComponent
    }

    fun inject(fragment: ArtObjectsFragment)

    fun inject(fragment: ArtObjectDetailsFragment)

}

interface ViewComponentFactoryProvider {
    fun provideViewComponentFactory(): ViewComponent.Factory
}
