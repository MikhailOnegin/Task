package com.example.task.presentation.presenters

import com.example.task.presentation.view.NewsView


interface NewsPresenter {
    fun attachView(view:NewsView)
    fun loadNews(page:Int)
    fun clear()
}