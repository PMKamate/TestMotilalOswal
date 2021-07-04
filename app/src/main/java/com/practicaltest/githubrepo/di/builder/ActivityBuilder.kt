package com.practicaltest.githubrepo.di.builder


import com.practicaltest.githubrepo.activity.main.MainActivity
import com.practicaltest.githubrepo.activity.detail.RepoDetailsActivityWeb
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindRepoDetailsActivityWeb(): RepoDetailsActivityWeb

}