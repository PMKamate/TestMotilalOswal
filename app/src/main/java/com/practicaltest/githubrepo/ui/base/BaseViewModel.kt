package com.practicaltest.githubrepo.ui.base

import androidx.lifecycle.ViewModel
import com.practicaltest.githubrepo.data.repository.RepoRepository
import java.lang.ref.WeakReference

/**
 * Created by amitshekhar on 07/07/17.
 */
abstract class BaseViewModel<N>(repository: RepoRepository) : ViewModel() {
    private val mRepository: RepoRepository
    /* val isLoading = ObservableBoolean()
     private val mSchedulerProvider: SchedulerProvider
     val compositeDisposable: CompositeDisposable*/
    private var mNavigator: WeakReference<N>? = null
    override fun onCleared() {
       // compositeDisposable.dispose()
        super.onCleared()
    }

    val repository: RepoRepository
        get() = mRepository

    /*  fun setIsLoading(isLoading: Boolean) {
          this.isLoading.set(isLoading)
      }*/

    val navigator: N?
        get() = mNavigator!!.get()

    fun setNavigator(navigator: N) {
        mNavigator = WeakReference(navigator)
    }

  /*  val schedulerProvider: SchedulerProvider
        get() = mSchedulerProvider*/

    init {
        mRepository = repository
       // mSchedulerProvider = schedulerProvider
       // compositeDisposable = CompositeDisposable()
    }
}