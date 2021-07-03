package com.practicaltest.githubrepo.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.*
import com.practicaltest.githubrepo.BR
import com.practicaltest.githubrepo.R
import com.practicaltest.githubrepo.adapter.RepoAdapter
import com.practicaltest.githubrepo.data.entities.RepoDetail
import com.practicaltest.githubrepo.databinding.ActivityMainBinding
import com.practicaltest.githubrepo.factory.ViewModelProviderFactory
import com.practicaltest.githubrepo.ui.base.BaseActivity
import com.practicaltest.githubrepo.utils.Resource
import com.practicaltest.githubrepo.workmanger.SyncWorker
import kotlinx.android.synthetic.main.activity_recycleview.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    MainLoginNavigator {

    @Inject
    lateinit var factory: ViewModelProviderFactory
    lateinit var mAfterLoginViewModel: MainViewModel
    private lateinit var adapter: RepoAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var mNewsDetails = ArrayList<RepoDetail>()
    private var currentPage = 0L

    companion object {
        private const val SYNC_WORK = "SyncWork"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setupObservers()
        setAdapter()
        setRVLayoutManager()
        initListener()
        setPeriodicWorkRequest()
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): MainViewModel {
        mAfterLoginViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        return mAfterLoginViewModel
    }

    private fun setAdapter() {
        adapter = RepoAdapter(mNewsDetails, this)
        adapter.notifyDataSetChanged()
        rvlist.adapter = adapter
    }

    private fun initListener() {
        adapter.setOnItemClickListener(object : RepoAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                val intent = Intent(applicationContext, RepoDetailsActivityWeb::class.java)
                val repoDetail: RepoDetail = mNewsDetails.get(position)
                intent.putExtra("url", repoDetail.htmlUrl)
                intent.putExtra("title", repoDetail.fullName)
                startActivity(intent)

            }
        })
    }

    private fun setRVLayoutManager() {
        linearLayoutManager = LinearLayoutManager(this)
        rvlist.layoutManager = linearLayoutManager
        rvlist.setHasFixedSize(true)
    }

    fun setPeriodicWorkRequest() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val periodicSyncDataWork =
            PeriodicWorkRequest.Builder(SyncWorker::class.java, 15, TimeUnit.MINUTES)
                .addTag(SYNC_WORK)
                .setConstraints(constraints) // setting a backoff on case the work needs to retry
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    PeriodicWorkRequest.MIN_BACKOFF_MILLIS,
                    TimeUnit.MILLISECONDS
                )
                .build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            SYNC_WORK,
            ExistingPeriodicWorkPolicy.KEEP,  //Existing Periodic Work policy
            periodicSyncDataWork //work request
        )
       /* WorkManager.getInstance(this).enqueue(
            OneTimeWorkRequestBuilder<SyncWorker>().build()
        )*/
    }

    private fun setupObservers() {
        mAfterLoginViewModel.getNewsDetails(currentPage).observe(this) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) {
                        adapter.setItems(ArrayList(it.data))
                    }
                }
                Resource.Status.ERROR -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(application, it.message, Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING ->
                    progress_bar.visibility = View.VISIBLE
            }
        }
    }
}