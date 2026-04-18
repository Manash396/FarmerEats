package com.mk.farmereats.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.Strictness
import com.mk.farmereats.data.remote.api.ApiService
import com.mk.farmereats.utils.AppConstants.BASE_URL
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun getBaseUrl(): String  = BASE_URL

    @Provides
    fun provideOkhttp() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            ).build()
    }

    @Provides
    fun provideRetrofit(
        baseUrl : String ,
        client: OkHttpClient
    ) : Retrofit {
        val gson: Gson = GsonBuilder()
            .setStrictness(Strictness.LENIENT)
            .create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create(gson)
            )
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit) : ApiService{
        return retrofit.create(ApiService::class.java)
    }

}