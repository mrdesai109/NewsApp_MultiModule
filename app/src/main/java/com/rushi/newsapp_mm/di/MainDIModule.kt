package com.rushi.newsapp_mm.di

import android.content.Context
import com.rushi.common_utils.Navigator
import com.rushi.news_data.database.NewsDao
import com.rushi.newsapp_mm.database.AppDatabase
import com.rushi.newsapp_mm.navigation.DefaultNavigatorProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainDIModule {

    @Provides
    @Singleton
    fun provideProvider() : Navigator.Provider{
        return DefaultNavigatorProvider()
    }

    @Provides
    @Singleton
    fun provideMainDB(@ApplicationContext ctx: Context) : AppDatabase{
        return AppDatabase.getInstance(ctx)
    }

    @Provides
    @Singleton
    fun provideNewsDao(appDatabase: AppDatabase) : NewsDao{
        return appDatabase.getNewsDao()
    }

}