package com.practicaltest.githubrepo.activity.detail

import com.practicaltest.githubrepo.data.repository.RepoRepository
import com.practicaltest.githubrepo.ui.base.BaseViewModel
import javax.inject.Inject

class RepoDetailViewModel @Inject internal constructor(
    repository: RepoRepository
) : BaseViewModel<RepoDetailNavigator>(repository = repository) {

}