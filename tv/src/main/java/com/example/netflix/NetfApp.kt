package com.example.netflix

import android.app.Application
import com.example.netflix.data.repositories.DiscoverDataRepo
import com.example.netflix.data.repositories.DiscoverDataRepoImpl
import com.example.netflix.ui.screen.discover.DiscoverDataApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@HiltAndroidApp
class NetfApp : Application()

@Module
@InstallIn(SingletonComponent::class)
abstract class DiscoverRepositoryModule {
    @Binds
    abstract fun bindMovieRepository(discoverDataRepoImpl: DiscoverDataRepoImpl): DiscoverDataRepo
}

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        val client = Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
                // Log request and response bodies
            })
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val requestWithHeaders = originalRequest.newBuilder()
                    .addHeader("x-rapidapi-key", "78d91e9c7emshc1d143c11a9fdd7p101fa3jsn3e8c325f91c6")
                    .addHeader("x-rapidapi-host", "tvview.p.rapidapi.com")
                    .build()
                chain.proceed(requestWithHeaders)
            }.build()

        return Retrofit.Builder()
            .baseUrl("https://tvview.p.rapidapi.com") // Replace with your API base URL
            .addConverterFactory(GsonConverterFactory.create()) // Or your preferred converter
            .client(client)
            .build()
//        retrofit.create(DiscoverDataApi::class.java)
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): DiscoverDataApi {
        return retrofit.create(DiscoverDataApi::class.java)
    }
}
