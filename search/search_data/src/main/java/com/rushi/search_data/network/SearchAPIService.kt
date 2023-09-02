package com.rushi.search_data.network

import com.rushi.search_data.model.BreakingNewsResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SearchAPIService {

    @GET("everything")
    suspend fun searchNews(@QueryMap map: MutableMap<String, String>): BreakingNewsResponse
}