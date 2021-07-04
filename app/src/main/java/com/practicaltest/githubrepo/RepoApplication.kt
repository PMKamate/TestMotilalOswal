package com.practicaltest.githubrepo

import androidx.lifecycle.LifecycleObserver
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.practicaltest.githubrepo.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class RepoApplication : MultiDexApplication(), HasAndroidInjector, LifecycleObserver {

    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = activityDispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        DaggerAppComponent.builder().application(this).build().inject(this)

    }
}