package com.practicaltest.githubrepo.data.repository

import android.util.Log
import com.practicaltest.githubrepo.data.entities.RepoDetail
import com.practicaltest.githubrepo.data.local.RepoDetailDao
import com.practicaltest.githubrepo.data.remote.RepoRemoteDataSource
import com.practicaltest.githubrepo.apiResponse.Item
import com.practicaltest.githubrepo.utils.RepoListModel
import com.practicaltest.githubrepo.utils.performGetOperation
import javax.inject.Inject
import javax.inject.Singleton

class RepoRepository @Inject constructor(
    private val remoteDataSource: RepoRemoteDataSource,
    private val repoDetailDao: RepoDetailDao
) {
    suspend fun saveDataIntoDB(repoListModel: RepoListModel) {
        repoDetailDao.insertAll(repoListModel.repoItemList)
    }


    fun getNewsDetails(page: Long, flag: Boolean) = performGetOperation(
        databaseQuery = { repoDetailDao.getAllUserDataItem() },
        networkCall = { remoteDataSource.fetchRepositories(page) },
        saveCallResult = {
            it.items?.let { it1 ->
                Log.d("Test: mainnwsize: ", "" + it1.size)
                val list = getNewsDBList(it1)
                saveDataIntoDB(list)
            }
        }
    )

    fun getNewsDBList(articleListResponse: List<Item?>): RepoListModel {
        val newsList = ArrayList<RepoDetail>()
        var id = 1
        articleListResponse.forEach { item ->
            id++
            newsList.add(
                RepoDetail(
                    id,
                    item?.name,
                    item?.fullName,
                    item?.htmlUrl,
                    item?.owner?.reposUrl,
                    item?.owner?.avatarUrl,
                    item?.description,
                    item?.createdAt,
                    item?.stargazersCount,
                    item?.watchers,
                    item?.forks,
                    item?.language
                )
            )
        }
        return RepoListModel(newsList)

    }

}