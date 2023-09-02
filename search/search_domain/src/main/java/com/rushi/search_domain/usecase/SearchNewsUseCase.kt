package com.rushi.search_domain.usecase

import com.rushi.common_utils.Resource
import com.rushi.search_domain.model.Article
import com.rushi.search_domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(val searchRepository: SearchRepository) {

    operator fun invoke(map: MutableMap<String, String>): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())
        try {
            val list1 = searchRepository.searchNews(map)
                .filter { (it.title != "Title Unavailable") && ((it.urlToImage != null) && (it.urlToImage.isNotEmpty())) }
            emit(Resource.Success(data = list1))
        } catch (e: Exception) {
            emit(
                Resource.Error(message = e.message ?: "Unknown error in Searching News")
            )
        }
    }
}