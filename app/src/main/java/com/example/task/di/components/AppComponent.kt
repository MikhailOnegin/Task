package com.example.task.di.components

import com.example.task.di.module.PresentersModule
import com.example.task.presentation.activity.MainActivity
import dagger.Component

@Component(modules = [PresentersModule::class])
interface AppComponent {

    // Activities
    fun inject(activity: MainActivity)

}