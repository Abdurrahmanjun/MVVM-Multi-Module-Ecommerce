package com.abdurrahmanjun.trolli

import android.app.Application
import com.abdurrahmanjun.core.di.CoreModuleContainer
import com.abdurrahmanjun.profile.di.ProfileModuleContainer
import com.abdurrahmanjun.trolli.di.AppModuleContainer
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TrolliApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val coreModuleContainer = CoreModuleContainer()
        val appModuleContainer = AppModuleContainer()
        val profileModuleContainer = ProfileModuleContainer()

        startKoin {
            androidContext(this@TrolliApplication)
            modules(
            coreModuleContainer.modules() +
                    appModuleContainer.modules() +
                    profileModuleContainer.modules()
            )
        }
    }
}