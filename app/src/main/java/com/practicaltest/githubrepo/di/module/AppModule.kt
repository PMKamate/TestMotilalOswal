package com.practicaltest.githubrepo.di.module

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.practicaltest.githubrepo.data.local.AppDatabase
import com.practicaltest.githubrepo.data.local.RepoDetailDao
import com.practicaltest.githubrepo.data.remote.NewsService
import com.practicaltest.githubrepo.data.remote.RepoRemoteDataSource
import com.practicaltest.githubrepo.data.repository.RepoRepository
import com.practicaltest.githubrepo.workmanger.RepositoryWorker
import com.practicaltest.githubrepo.workmanger.SaveRepoRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(JacksonConverterFactory.create())
        .build()


    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideJackSon(): JacksonConverterFactory = JacksonConverterFactory.create()

    @Provides
    fun provideUserService(retrofit: Retrofit): NewsService = retrofit.create(
        NewsService::class.java
    )

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(newsService: NewsService) =
        RepoRemoteDataSource(newsService)

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideDatabase(appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.repoDetailDao()


    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RepoRemoteDataSource,
        repoDetailDao: RepoDetailDao
    ) =
        RepoRepository(remoteDataSource, repoDetailDao)

   /* @Singleton
    @Provides
    fun provideSaveRepository(
        repoDetailDao: RepoDetailDao
    ) =
        SaveRepoRepository(repoDetailDao)*/

   /* @Singleton
    @Provides
    fun provideSaveRepository(
        saveRepoRepository: SaveRepoRepository
    ) =
        RepositoryWorker(saveRepoRepository)*/

}