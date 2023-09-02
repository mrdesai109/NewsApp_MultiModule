package com.rushi.search_data.model

data class BreakingNewsResponse(
    val articles: List<ArticleDTO>,
    val status: String,
    val totalResults: Int
)