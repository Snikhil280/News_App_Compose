package com.nikhil.newsAppCompose.data.remote.api

import com.nikhil.newsAppCompose.data.remote.dto.NewsDto
import com.nikhil.newsAppCompose.common.Constants.API_KEY
import com.nikhil.newsAppCompose.common.Constants.TOP_HEADLINES
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET(TOP_HEADLINES)
    suspend fun getNews(
        @Query("country") q:String = "in",
        @Query("apiKey") key:String = API_KEY
    ): NewsDto


}