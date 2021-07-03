package com.practicaltest.githubrepo.data.remote

import com.practicaltest.githubrepo.apiResponse.RepositoryData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
     @GET("search/repositories")
    suspend fun fetchRepositories(
        @Query("sort") sort: String?,
        @Query("order") order: String?,
        @Query("q") q: String?,
        @Query("page") page: Long?
    ) :  Response<RepositoryData>
}