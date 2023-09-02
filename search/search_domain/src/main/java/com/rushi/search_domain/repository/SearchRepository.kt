package com.rushi.search_domain.repository

import com.rushi.search_domain.model.Article

interface SearchRepository {

    suspend fun searchNews(map : MutableMap<String,String>) : List<Article>
}