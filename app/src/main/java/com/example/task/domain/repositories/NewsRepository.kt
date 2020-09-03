package com.example.task.domain.repositories

import com.example.task.domain.pojo.Everything
import io.reactivex.Single

interface NewsRepository {

    fun getEverything(page:Int): Single<Everything>
}