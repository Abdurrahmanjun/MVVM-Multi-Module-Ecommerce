package com.abdurrahmanjun.trolli

import android.app.Application
import com.abdurrahmanjun.core.di.CoreModuleContainer
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TrolliApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val coreModuleContainer = CoreModuleContainer()

        startKoin {
            androidContext(this@TrolliApplication)
            modules(
                coreModuleContainer.modules()
            )
        }
    }
}