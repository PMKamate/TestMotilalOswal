package com.practicaltest.githubrepo.data.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "repoDetails")
class RepoDetail(
    @NonNull
    @PrimaryKey
    val id: Int? = null,
    val name: String? = null,
    val fullName: String? = null,
    val htmlUrl: String? = null,
    val repoUrl: String? = null,
    var avatarUrl: String? = null,
    val description: String? = null,
    val createdAt: String? = null,
    val starsCount: Long? = null,
    val watchers: Long? = null,
    val forks: Long? = null,
    val language: String? = null
)