package com.example.weather

import android.app.Application
import com.example.weather.di.DaggerComponent
import com.example.weather.di.DaggerDaggerComponent
import com.example.weather.di.PresentationModule

class App : Application() {
    companion object {
        lateinit var daggerComponent: DaggerComponent
    }

    override fun onCreate() {
        super.onCreate()

        daggerComponent = DaggerDaggerComponent.builder()
            .presentationModule(PresentationModule(this))
            .build()
    }
}