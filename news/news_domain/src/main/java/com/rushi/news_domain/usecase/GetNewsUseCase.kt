package com.rushi.news_domain.usecase

import com.rushi.common_utils.Resource
import com.rushi.news_domain.model.Article
import com.rushi.news_domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetNewsUseCase constructor(val newsRepository: NewsRepository) {

    operator fun invoke() : Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())
        try{
            emit(Resource.Success(data = newsRepository.getNewsArticles().filter { (it.title != "Title Unavailable") && (it.content != "Content Unavailable") && ((it.urlToImage != null) && it.urlToImage.isNotEmpty()) }))
        }catch (ex:Exception){
            emit(Resource.Error(message = ex.message ?: "Unknown Error - GetNews"))
        }
    }
}