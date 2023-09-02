package com.rushi.news_data.mapper

import com.rushi.news_data.model.ArticleDTO
import com.rushi.news_domain.model.Article

//this is why we need domain -> data dependency.

fun ArticleDTO.toArticle() = Article(
    author ?: "Author Unavailable",
    content ?: "Content Unavailable",
    description ?: "Desc Unavailable",
    title ?: "Title Unavailable",
    urlToImage
)