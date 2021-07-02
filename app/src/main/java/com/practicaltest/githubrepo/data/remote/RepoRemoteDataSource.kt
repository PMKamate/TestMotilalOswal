package com.practicaltest.githubrepo.data.remote

import com.practicaltest.githubrepo.utils.AppConstants
import retrofit2.http.GET
import javax.inject.Inject

class RepoRemoteDataSource @Inject constructor(
    private val newsService: NewsService
) : BaseDataSource() {

    @GET("search/repositories")
    suspend fun fetchRepositories(
        page: Long
    ) = getResult {
        newsService.fetchRepositories(
            AppConstants.QUERY_SORT,
            AppConstants.QUERY_ORDER,
            AppConstants.QUERY_API,
            page
        )
    }
}