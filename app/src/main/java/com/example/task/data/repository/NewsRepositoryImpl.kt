package com.example.task.data.repository

import com.example.task.domain.pojo.Everything
import com.example.task.domain.repositories.NewsRepository
import com.example.thecats.api.ApiFactory
import io.reactivex.Single
import javax.inject.Inject

class NewsRepositoryImpl:NewsRepository {


    override fun getEverything(page:Int): Single<Everything> {
       return ApiFactory.apiService.getEverything(page = page)
    }
}