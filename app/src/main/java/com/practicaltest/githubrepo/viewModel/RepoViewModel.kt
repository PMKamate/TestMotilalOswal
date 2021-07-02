package com.practicaltest.githubrepo.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.practicaltest.githubrepo.data.entities.RepoDetail
import com.practicaltest.githubrepo.data.repository.RepoRepository
import com.practicaltest.githubrepo.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepoViewModel @Inject constructor(
    private val repository: RepoRepository
) : ViewModel() {
    fun getNewsDetails(page:Long): LiveData<Resource<List<RepoDetail>>> {
        Log.d("Test: ","page: "+page)
        return repository.getNewsDetails(page)
    }
}
