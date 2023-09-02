package com.rushi.news_data.model

data class BreakingNewsResponse(
    val articles: List<ArticleDTO>,
    val status: String,
    val totalResults: Int
)