package com.rushi.news_domain.repository

import com.rushi.news_domain.model.Article

interface NewsRepository {

    suspend fun getNewsArticles() : List<Article>
}