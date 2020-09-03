package com.example.task.di.module

import com.example.task.data.repository.NewsRepositoryImpl
import com.example.task.domain.repositories.NewsRepository
import com.example.task.presentation.activity.MainActivity
import com.example.task.presentation.presenters.NewsPresenter
import com.example.task.presentation.presenters.NewsPresenterImpl
import com.example.task.presentation.view.NewsView
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresentersModule {

    @Provides
    fun provideNewsRepository():NewsRepository{
        return NewsRepositoryImpl()
    }

    @Provides
    fun provideNewsPresenter(repository: NewsRepository): NewsPresenter {
        return NewsPresenterImpl(repository)
    }
}