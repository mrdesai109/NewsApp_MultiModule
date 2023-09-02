package com.rushi.search_data.mappers

import com.rushi.search_data.model.ArticleDTO
import com.rushi.search_domain.model.Article

//this is why we need domain -> data dependency.

fun ArticleDTO.toArticle() = Article(
    author ?: "Author Unavailable",
    content ?: "Content Unavailable",
    description ?: "Desc Unavailable",
    title ?: "Title Unavailable",
    urlToImage
)