package com.rushi.news_presentation

import com.rushi.news_domain.model.Article

data class NewsState(
    var isLoading: Boolean = false,
    var errorMsg: String = "",
    val data: List<Article> = emptyList()
)
