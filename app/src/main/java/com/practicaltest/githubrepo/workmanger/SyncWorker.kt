package com.practicaltest.githubrepo.workmanger

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.practicaltest.githubrepo.data.repository.RepoRepository
import com.practicaltest.githubrepo.apiResponse.RepositoryData
import com.practicaltest.githubrepo.utils.AppConstants
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class SyncWorker @Inject constructor(val context: Context, workParams: WorkerParameters) :
    Worker(context, workParams) {

    /*@Inject
    lateinit var repository: RepoRepository*/
    @Inject lateinit var repository: RepositoryWorker

    init {
        Provider.appComponent?.inject(this)
    }

    override fun doWork(): Result {
        return try {
            Log.d("Test: ", " doWork called: ")
            getNewsDetails(1L, context)
        } catch (e: Exception) {
            Result.failure()
        }
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
                    Log.d("Test: workersize: ", "" + it1.size  )
                    val list = repository.repoRepository.getNewsDBList(it1)
                    GlobalScope.launch(Dispatchers.IO) {
                        Log.d("Test: ", " worker: " + list)
                        repository.repoRepository.saveDataIntoDB(list)
                    }

                }
                Log.d("Test: ", " worker: " + it.items?.size)
                result = Result.success()
            }, {
                Log.d("Test: ", " exception: " + it)
                result = Result.failure()

            })

        return result
    }
}
