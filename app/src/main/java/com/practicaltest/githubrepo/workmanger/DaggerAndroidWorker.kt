package com.practicaltest.githubrepo.workmanger

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.practicaltest.githubrepo.apiResponse.RepositoryData
import com.practicaltest.githubrepo.utils.AppConstants
import com.rx2androidnetworking.Rx2AndroidNetworking
import dagger.android.ContributesAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


object ContextInjection {
    @JvmStatic
    fun inject(to: Any, with: Context) {
        (with.applicationContext as HasAndroidInjector).androidInjector().inject(to)
    }
}

class DaggerAndroidWorker(
   private var context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    @Inject
    lateinit var repository: RepositoryWorker

    init {
        ContextInjection.inject(to = this, with = context)
    }

    override fun doWork(): Result {
       Log.d("Test", "Injected repository: $repository")
        getNewsDetails(1L, context)
        return Result.success()
    }


    @dagger.Module
    interface Module {
        @ContributesAndroidInjector
        fun worker(): DaggerAndroidWorker
    }
    @SuppressLint("CheckResult")
    fun getNewsDetails(page: Long, context: Context): Result {
        var result: Result = Result.failure()
        Rx2AndroidNetworking.get("https://api.github.com/search/repositories")
            .addQueryParameter("sort", AppConstants.QUERY_SORT)
            .addQueryParameter("order", AppConstants.QUERY_ORDER)
            .addQueryParameter("q", AppConstants.QUERY_API)
            .addQueryParameter("page", page.toString())
            .build()
            .getObjectObservable(RepositoryData::class.java)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Toast.makeText(context, "Background sync service called ", Toast.LENGTH_SHORT).show()
                it.items?.let { it1 ->
                    val list = repository.repoRepository.getNewsDBList(it1)
                    GlobalScope.launch(Dispatchers.IO) {
                        repository.repoRepository.saveDataIntoDB(list)
                    }

                }
                result = Result.success()
            }, {
                result = Result.failure()

            })

        return result
    }
}