package com.example.thecats.api

import com.example.task.domain.pojo.Everything
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    fun getEverything(
        @Query(TITLE) title:String = "sports",
        @Query(PAGE_SIZE) pageSize:String= "10",
        @Query(PAGE) page:Int = 1,
        @Query(API_KEY) apiKey: String ="e7a4d3493ec84a1a9232789bf7a943cf"
    ):Single<Everything>


    companion object {
        private const val PAGE = "PAGE"
        private const val TITLE ="q"
        private const val LANGUAGE = "language"
        private const val API_KEY = "apiKey"
        private const val SOURCES = "sources"
        private const val COUNTRY = "country"
        private const val PAGE_SIZE = "pageSize"
    }
}