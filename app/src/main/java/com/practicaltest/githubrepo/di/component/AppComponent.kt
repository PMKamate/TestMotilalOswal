package com.practicaltest.githubrepo.di.component

import android.app.Application
import com.practicaltest.githubrepo.RepoApplication
import com.practicaltest.githubrepo.di.builder.ActivityBuilder
import com.practicaltest.githubrepo.di.module.AppModule
import com.practicaltest.githubrepo.workmanger.SyncWorker
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class])
interface AppComponent: AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
    fun inject(app: RepoApplication)
    fun inject(worker: SyncWorker)
    override fun inject(instance: DaggerApplication)
}