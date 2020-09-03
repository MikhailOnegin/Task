package com.example.task.presentation.view

import com.example.task.data.helpers.enums.Error
import com.example.task.domain.pojo.Article

interface NewsView {

    fun showNews(news:List<Article>)
    fun showError(error: Error)

}