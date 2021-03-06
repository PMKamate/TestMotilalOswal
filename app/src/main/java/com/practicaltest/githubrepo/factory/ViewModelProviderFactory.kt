package com.practicaltest.githubrepo.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practicaltest.githubrepo.activity.detail.RepoDetailViewModel
import com.practicaltest.githubrepo.activity.main.MainViewModel
import com.practicaltest.githubrepo.data.repository.RepoRepository
import javax.inject.Inject

class ViewModelProviderFactory @Inject internal constructor(
   var repository: RepoRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(RepoDetailViewModel::class.java)) {
            return RepoDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}