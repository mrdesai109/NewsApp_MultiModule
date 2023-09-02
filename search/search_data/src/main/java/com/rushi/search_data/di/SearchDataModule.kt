package com.rushi.search_data.di

import com.rushi.search_data.network.SearchAPIService
import com.rushi.search_data.repository.SearchRepositoryImpl
import com.rushi.search_domain.repository.SearchRepository
import com.rushi.search_domain.usecase.SearchNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchDataModule {

    @Singleton
    @Provides
    fun provideSearchAPIService(retrofit: Retrofit): SearchAPIService =
        retrofit.create(SearchAPIService::class.java)

    @Singleton
    @Provides
    fun provideSearchRepository(searchAPIService: SearchAPIService): SearchRepository {
        return SearchRepositoryImpl(searchAPIService)
    }

}