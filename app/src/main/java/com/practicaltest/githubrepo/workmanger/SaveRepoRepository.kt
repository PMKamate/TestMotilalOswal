package com.practicaltest.githubrepo.workmanger

import android.util.Log
import com.practicaltest.githubrepo.apiResponse.Item
import com.practicaltest.githubrepo.data.entities.RepoDetail
import com.practicaltest.githubrepo.data.local.AppDatabase
import com.practicaltest.githubrepo.data.local.RepoDetailDao
import com.practicaltest.githubrepo.utils.RepoListModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveRepoRepository @Inject constructor(
    private val appDatabase: AppDatabase
    ) {
    suspend fun saveDataIntoDB(repoListModel: RepoListModel) {
        appDatabase.repoDetailDao().insertAll(repoListModel.repoItemList)
    }
    fun getNewsDBList(itemList: List<Item?>): RepoListModel {
        val newsList = ArrayList<RepoDetail>()
        var id = 1
        itemList.forEach { item ->
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
        Log.d("Test", "worker list: "+newsList.size)

        return RepoListModel(newsList)

    }
}
