package com.rushi.news_data.network

import com.rushi.common_utils.API_KEY
import com.rushi.common_utils.CATEGORY
import com.rushi.common_utils.COUNTRY
import com.rushi.news_data.model.BreakingNewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {

    //GET https://newsapi.org/v2/top-headlines?country=us&apiKey=00e23f8f25d94d60a50bb2a9957ac455

    @GET("top-headlines")
    suspend fun getNewsArticles(
        @Query("country") country: String = COUNTRY,
        @Query("category") category: String = CATEGORY,
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("pageSize") pageSize: Int = 50
    ): BreakingNewsResponse
}