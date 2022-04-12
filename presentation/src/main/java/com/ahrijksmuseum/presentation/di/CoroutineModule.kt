package com.ahrijksmuseum.presentation.di

import com.ahrijksmuseum.presentation.coroutines.CoroutineContextProvider
import com.ahrijksmuseum.presentation.coroutines.DefaultCoroutineContextProvider
import dagger.Module
import dagger.Provides

@Module
class CoroutineModule {

    @Provides
    fun provideCoroutineContextProvider(): CoroutineContextProvider {
        return DefaultCoroutineContextProvider()
    }

}