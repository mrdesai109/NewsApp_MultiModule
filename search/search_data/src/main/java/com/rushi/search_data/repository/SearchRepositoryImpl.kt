package com.rushi.search_data.repository

import com.rushi.search_data.mappers.toArticle
import com.rushi.search_data.network.SearchAPIService
import com.rushi.search_domain.model.Article
import com.rushi.search_domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(val searchAPIService: SearchAPIService) :
    SearchRepository {

    override suspend fun searchNews(map: MutableMap<String, String>): List<Article> {
        return searchAPIService.searchNews(map).articles.map { it.toArticle() }
    }
}