package com.rushi.search_domain.di

import com.rushi.search_domain.repository.SearchRepository
import com.rushi.search_domain.usecase.SearchNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SearchDomainModule {

    @Singleton
    @Provides
    fun provideSearchUseCase(searchRepository: SearchRepository): SearchNewsUseCase =
        SearchNewsUseCase(searchRepository)
}