package com.ahrijksmuseum.app

import android.app.Application
import com.ahrijksmuseum.app.di.AppComponent
import com.ahrijksmuseum.app.di.DaggerAppComponent
import com.ahrijksmuseum.view.di.ViewComponent
import com.ahrijksmuseum.view.di.ViewComponentFactoryProvider

class AhRijksMuseumApplication : Application(), ViewComponentFactoryProvider {

    // DI components
    private val _appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun provideViewComponentFactory(): ViewComponent.Factory =
        _appComponent.viewComponentFactory()

}
