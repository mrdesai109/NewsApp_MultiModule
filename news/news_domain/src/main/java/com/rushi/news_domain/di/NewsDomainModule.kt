package com.rushi.news_domain.di

import com.rushi.news_domain.repository.NewsRepository
import com.rushi.news_domain.usecase.GetNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsDomainModule {

    @Provides
    @Singleton
    fun provideGetNewsUseCase(newsRepository: NewsRepository): GetNewsUseCase = GetNewsUseCase(
        newsRepository = newsRepository
    )

}