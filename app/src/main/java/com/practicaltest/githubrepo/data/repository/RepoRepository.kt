package com.practicaltest.githubrepo.data.repository

import android.util.Log
import com.practicaltest.githubrepo.data.entities.RepoDetail
import com.practicaltest.githubrepo.data.local.RepoDetailDao
import com.practicaltest.githubrepo.data.remote.RepoRemoteDataSource
import com.practicaltest.githubrepo.repository.Item
import com.practicaltest.githubrepo.utils.RepoListModel
import com.practicaltest.githubrepo.utils.performGetOperation
import javax.inject.Inject

class RepoRepository @Inject constructor(
    private val remoteDataSource: RepoRemoteDataSource,
    private val repoDetailDao: RepoDetailDao
) {

    fun getNewsDetails(page: Long) = performGetOperation(
        databaseQuery = { repoDetailDao.getAllUserDataItem() },
        networkCall = { remoteDataSource.fetchRepositories(page) },
        saveCallResult = {
            it.items?.let { it1 ->
                Log.d("Test nwsize: ", "" + it1.size)

                val list = getNewsDBList(it1)
                repoDetailDao.insertAll(list.repoItemList)
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
        Log.d("Test dbsize: ", "" + newsList.size)
        return RepoListModel(newsList)

    }

}