package com.practicaltest.githubrepo.activity

import androidx.lifecycle.LiveData
import com.practicaltest.githubrepo.data.entities.RepoDetail
import com.practicaltest.githubrepo.data.repository.RepoRepository
import com.practicaltest.githubrepo.ui.base.BaseViewModel
import com.practicaltest.githubrepo.utils.Resource
import javax.inject.Inject

class MainViewModel @Inject internal constructor(
    repository: RepoRepository
) : BaseViewModel<MainLoginNavigator>(repository = repository) {
    fun getNewsDetails(page: Long): LiveData<Resource<List<RepoDetail>>> {
        return repository.getNewsDetails(page, false)
    }

}