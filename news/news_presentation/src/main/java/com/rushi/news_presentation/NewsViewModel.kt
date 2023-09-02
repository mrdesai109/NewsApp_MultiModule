package com.rushi.news_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rushi.common_utils.Resource
import com.rushi.news_domain.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(val getNewsUseCase: GetNewsUseCase) : ViewModel() {

    private val _newsState = MutableStateFlow(NewsState())
    val newsState: StateFlow<NewsState> = _newsState

    init {
        getNewsArticles()
    }

    fun getNewsArticles() {
        viewModelScope.launch {
            getNewsUseCase().collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _newsState.value =
                            newsState.value.copy(isLoading = false, errorMsg = resource.message)
                    }

                    is Resource.Loading -> {
                        _newsState.value = newsState.value.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        _newsState.value = newsState.value.copy(
                            isLoading = false,
                            data = resource.data ?: emptyList()
                        )
                    }
                }
            }
        }
    }
}