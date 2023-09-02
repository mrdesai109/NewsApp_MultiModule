package com.rushi.search_presentation

import com.rushi.search_domain.model.Article

data class SearchState(
    val isLoading:Boolean = false,
    val errMsg: String = "",
    val data: List<Article> = emptyList()
)
