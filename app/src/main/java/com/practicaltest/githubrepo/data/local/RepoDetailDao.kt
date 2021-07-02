package com.practicaltest.githubrepo.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.practicaltest.githubrepo.data.entities.RepoDetail

@Dao
interface RepoDetailDao {

    @Query("SELECT * FROM repoDetails")
    fun getAllUserDataItem() : LiveData<List<RepoDetail>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repoDetail: List<RepoDetail>)

}