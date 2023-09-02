package com.rushi.news_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rushi.news_domain.model.Article

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: List<Article>)

    @Query("select * from article")
    suspend fun getNewsList() : List<Article>

    @Query("delete from article")
    suspend fun purgeNewsArticleTable()
}