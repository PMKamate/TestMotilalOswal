package com.practicaltest.githubrepo.data.remote

import com.practicaltest.githubrepo.repository.RepositoryData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
   /* @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String?,
        @Query("apiKey") apiKey: String?
    ): Response<News>*/

    @GET("search/repositories")
    suspend fun fetchRepositories(
        @Query("sort") sort: String?,
        @Query("order") order: String?,
        @Query("q") q: String?,
        @Query("page") page: Long?,
    ) :  Response<RepositoryData>
}