package com.rushi.news_data.repository

import com.rushi.news_data.database.NewsDao
import com.rushi.news_data.mapper.toArticle
import com.rushi.news_data.network.NewsAPIService
import com.rushi.news_domain.model.Article
import com.rushi.news_domain.repository.NewsRepository
import javax.inject.Inject

//this is data layer of 'news' module

class NewsRepositoryImpl @Inject constructor(val apiService : NewsAPIService, val newsDao: NewsDao) : NewsRepository {
    override suspend fun getNewsArticles(): List<Article> {
        return try{
            val list1 = apiService.getNewsArticles().articles.map {
                it.toArticle()
            }
            if(list1.isNotEmpty()){
                newsDao.purgeNewsArticleTable()
            }
            newsDao.insertList(list1)
            newsDao.getNewsList()
        }catch (e:Exception){
            newsDao.getNewsList()
        }
    }
}