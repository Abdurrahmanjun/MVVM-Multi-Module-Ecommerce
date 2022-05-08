package com.abdurrahmanjun.core.di

import com.abdurrahmanjun.core.local.DataPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class CoreModuleContainer : ModuleContainer() {
    private val preferencesModule = module {
        single { DataPreferences(androidContext()) }
    }
}