package com.rushi.news_data.di

import com.rushi.news_data.database.NewsDao
import com.rushi.news_data.network.NewsAPIService
import com.rushi.news_data.repository.NewsRepositoryImpl
import com.rushi.news_domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsDataModule {

    @Provides
    @Singleton
    fun provideNewsService(retrofit : Retrofit) : NewsAPIService = retrofit.create(NewsAPIService::class.java)

    @Provides
    @Singleton
    fun provideNewsRepository(newsAPIService: NewsAPIService,newsDao: NewsDao) : NewsRepository = NewsRepositoryImpl(newsAPIService,newsDao)
}