package com.ahrijksmuseum.data.di

import com.ahrijksmuseum.data.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
object NetworkModule {

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create().withNullSerialization())
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }

                addInterceptor { chain ->
                    val original = chain.request()
                    val originalHttpUrl = original.url

                    val url = originalHttpUrl.newBuilder()
                        .addQueryParameter("key", API_KEY)
                        .build()

                    val requestBuilder: Request.Builder = original.newBuilder()
                        .url(url)

                    val request: Request = requestBuilder.build()
                    chain.proceed(request)
                }
            }
            .callTimeout(TIMEOUT_IN_SECS, TimeUnit.SECONDS)
            .build()
    }

    private const val API_KEY = "0fiuZFh4"
    private const val BASE_URL = "https://www.rijksmuseum.nl/api/nl/"
    private const val TIMEOUT_IN_SECS = 30L

}
