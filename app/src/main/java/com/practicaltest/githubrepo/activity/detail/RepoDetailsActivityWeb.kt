package com.practicaltest.githubrepo.activity.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.practicaltest.githubrepo.BR
import com.practicaltest.githubrepo.R
import com.practicaltest.githubrepo.activity.main.MainNavigator
import com.practicaltest.githubrepo.activity.main.MainViewModel
import com.practicaltest.githubrepo.databinding.ActivityMainBinding
import com.practicaltest.githubrepo.databinding.ActivityRepoWebdetailBinding
import com.practicaltest.githubrepo.factory.ViewModelProviderFactory
import com.practicaltest.githubrepo.ui.base.BaseActivity
import javax.inject.Inject

class RepoDetailsActivityWeb : BaseActivity<ActivityRepoWebdetailBinding, RepoDetailViewModel>(),
    RepoDetailNavigator {
    private var mUrl: String? = null
    private var mTitle: String? = null
    lateinit var mRepoDetailViewModel: RepoDetailViewModel

    @Inject
    lateinit var factory: ViewModelProviderFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent: Intent = getIntent()
        mUrl = intent.getStringExtra("url")
        mTitle = intent.getStringExtra("title")
        supportActionBar?.title = mTitle
        initWebView(mUrl)
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_repo_webdetail
    }

    override fun getViewModel(): RepoDetailViewModel {
        mRepoDetailViewModel = ViewModelProvider(this, factory).get(RepoDetailViewModel::class.java)
        return mRepoDetailViewModel
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(url: String?) {
        val webView: WebView = findViewById(R.id.webView)
        webView.settings.loadsImagesAutomatically = true
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.setSupportZoom(true)
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url!!)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFinishAfterTransition()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
