package com.practicaltest.githubrepo.di.component

import android.app.Application
import com.practicaltest.githubrepo.RepoApplication
import com.practicaltest.githubrepo.di.builder.ActivityBuilder
import com.practicaltest.githubrepo.di.module.AppModule
import com.practicaltest.githubrepo.workmanger.DaggerAndroidWorker
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class, DaggerAndroidWorker.Module::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
    fun inject(app: RepoApplication)

}