package com.example.myapplication.di.modules

import com.example.myapplication.api.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder()
            .serializeNulls()

            .excludeFieldsWithoutExposeAnnotation().create()
    }

    @Provides
    @Singleton
    internal  fun provideOkhttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    @Singleton
    internal  fun provideRetrofitBuilder(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(provideGson()))
        .baseUrl("https://data.cityofnewyork.us/")
        .client(provideOkhttpClient())
        .build()

    @Provides
    @Singleton
    internal  fun provideApiModule(retrofit: Retrofit): ApiService = retrofit.create(
        ApiService::class.java
    )
}