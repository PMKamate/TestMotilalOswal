package com.practicaltest.githubrepo.ui.base

import androidx.lifecycle.ViewModel
import com.practicaltest.githubrepo.data.repository.RepoRepository
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>(repository: RepoRepository) : ViewModel() {
    private val mRepository: RepoRepository
    private var mNavigator: WeakReference<N>? = null
    override fun onCleared() {
        super.onCleared()
    }
    val repository: RepoRepository
        get() = mRepository

    init {
        mRepository = repository
    }
}