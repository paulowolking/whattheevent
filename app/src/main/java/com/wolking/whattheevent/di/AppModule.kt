package com.wolking.whattheevent.di

import android.app.Application
import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.internal.bind.DateTypeAdapter
import com.wolking.whattheevent.App
import com.wolking.whattheevent.BuildConfig
import com.wolking.whattheevent.data.core.ApiInterceptor
import com.wolking.whattheevent.data.core.ApiService
import com.wolking.whattheevent.data.event.repositories.EventRepositoryImpl
import com.wolking.whattheevent.data.shared.CoroutineDispatchersImpl
import com.wolking.whattheevent.domain.event.repository.EventRepository
import com.wolking.whattheevent.domain.shared.CoroutineDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL = "https://5f5a8f24d44d640016169133.mockapi.io/api/"

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideApp(application: Application): App {
        return application as App
    }

    @Singleton
    @Provides
    fun provideContext(app: App): Context = app

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(ApiInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                this.level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
            })
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create(
            GsonBuilder()
                .registerTypeAdapter(Date::class.java, DateTypeAdapter())
                .create()
        )

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideCoroutineDispatchers(): CoroutineDispatchers =
        CoroutineDispatchersImpl()

    @Provides
    fun providesEventRepository(
        apiService: ApiService,
        coroutineDispatchers: CoroutineDispatchersImpl
    ): EventRepository =
        EventRepositoryImpl(
            apiService = apiService,
            coroutineDispatchers = coroutineDispatchers
        )
}