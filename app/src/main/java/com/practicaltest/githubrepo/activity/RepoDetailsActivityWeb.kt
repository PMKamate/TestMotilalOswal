package com.practicaltest.githubrepo.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.practicaltest.githubrepo.R

class RepoDetailsActivityWeb : AppCompatActivity() {
    private var mUrl: String? = null
    private var mTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_webdetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent: Intent = getIntent()
        mUrl = intent.getStringExtra("url")
        mTitle = intent.getStringExtra("title")
        supportActionBar?.title = mTitle
        initWebView(mUrl)
    }

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
