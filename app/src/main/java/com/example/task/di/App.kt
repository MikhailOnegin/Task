package com.example.task.di

import android.app.Application
import com.example.task.di.components.AppComponent
import com.example.task.di.components.DaggerAppComponent

class App:Application() {



    companion object{
        lateinit var appComponent:AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }
}