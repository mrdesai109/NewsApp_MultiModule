package com.rushi.search_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rushi.common_utils.Resource
import com.rushi.search_domain.usecase.SearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val searchNewsUseCase: SearchNewsUseCase) : ViewModel() {

    private val _searchState = MutableStateFlow(SearchState())
    val searchState: StateFlow<SearchState> = _searchState

    fun searchNews(map: MutableMap<String, String>) {
        viewModelScope.launch {
            searchNewsUseCase(map).collect { resource ->
                when(resource){
                    is Resource.Error -> {
                        _searchState.value = searchState.value.copy(isLoading = false, errMsg = resource.message)
                    }
                    is Resource.Loading -> {
                        _searchState.value = searchState.value.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        _searchState.value = searchState.value.copy(isLoading = false, data = resource.data ?: emptyList())
                    }
                }
            }
        }
    }
}