package com.example.rickandmortywithcompose.di

import com.example.rickandmortywithcompose.data.repository.remote.ApiRepositoryImpl
import com.example.rickandmortywithcompose.data.datasource.remote.ApiService
import com.example.rickandmortywithcompose.data.datasource.remote.ApiURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {
    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(ApiURL.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesApiRepositoryImpl(apiService: ApiService) : ApiRepositoryImpl = ApiRepositoryImpl(apiService)
}