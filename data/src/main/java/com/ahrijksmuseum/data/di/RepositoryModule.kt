package com.ahrijksmuseum.data.di

import android.content.Context
import androidx.room.Room
import com.ahrijksmuseum.data.network.RijksMuseumApi
import com.ahrijksmuseum.data.repository.RepositoryImpl
import com.ahrijksmuseum.data.repository.datasources.local.AppDatabase
import com.ahrijksmuseum.data.repository.datasources.local.LocalDataSource
import com.ahrijksmuseum.data.repository.datasources.remote.RemoteDataSource
import com.ahrijksmuseum.domain.Repository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object RepositoryModule {

    @Provides
    fun provideRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): Repository {
        return RepositoryImpl(localDataSource, remoteDataSource)
    }

    @Provides
    fun provideRemoteDataSource(rijksMuseumApi: RijksMuseumApi): RemoteDataSource {
        return RemoteDataSource(rijksMuseumApi)
    }

    @Provides
    fun provideRijksMuseumApi(retrofit: Retrofit): RijksMuseumApi {
        return retrofit.create(RijksMuseumApi::class.java)
    }

    @Provides
    fun provideLocalDataSource(appDatabase: AppDatabase): LocalDataSource {
        return LocalDataSource(appDatabase)
    }

    @Provides
    fun provideAppDatabase(applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }
}