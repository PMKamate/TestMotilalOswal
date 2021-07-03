package com.practicaltest.githubrepo.workmanger

import com.practicaltest.githubrepo.data.repository.RepoRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryWorker @Inject constructor(
        val repoRepository: SaveRepoRepository
)
