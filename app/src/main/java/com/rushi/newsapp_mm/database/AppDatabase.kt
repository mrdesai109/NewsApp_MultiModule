package com.rushi.newsapp_mm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rushi.news_data.database.NewsDao
import com.rushi.news_domain.model.Article

@Database(entities = arrayOf(Article::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getNewsDao() : NewsDao

    companion object{
        fun getInstance(context: Context) : AppDatabase{
            return Room.databaseBuilder(context, AppDatabase::class.java,"app_db").fallbackToDestructiveMigration().build()
        }
    }
}