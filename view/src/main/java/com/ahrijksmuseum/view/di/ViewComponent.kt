package com.ahrijksmuseum.view.di

import dagger.Subcomponent

@Subcomponent
@FragmentScope
interface ViewComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ViewComponent
    }

}

interface ViewComponentFactoryProvider {
    fun provideViewComponentFactory(): ViewComponent.Factory
}
