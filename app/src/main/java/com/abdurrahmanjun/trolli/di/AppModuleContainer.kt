package com.abdurrahmanjun.trolli.di

import com.abdurrahmanjun.core.di.ModuleContainer
import com.abdurrahmanjun.trolli.features.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class AppModuleContainer : ModuleContainer() {

    private val viewModelModule = module {
        viewModel { SplashViewModel(get()) }
    }
}