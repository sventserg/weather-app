package com.example.weather.di

import com.example.weather.presentation.viewModel.MainViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Dagger component interface
 */

@Singleton
@Component (modules = [DataModule::class, DomainModule::class, PresentationModule::class] )
interface DaggerComponent {
    fun mainViewModel() : MainViewModel
}