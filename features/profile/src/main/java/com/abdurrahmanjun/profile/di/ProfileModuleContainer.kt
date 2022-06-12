package com.abdurrahmanjun.profile.di

import com.abdurrahmanjun.core.di.ModuleContainer
import com.abdurrahmanjun.core.remote.RetrofitProvider
import com.abdurrahmanjun.profile.data.ProfilerDataFactory
import com.abdurrahmanjun.profile.data.ProfileRepositoryImpl
import com.abdurrahmanjun.profile.data.remote.ProfileWebServices
import com.abdurrahmanjun.profile.domain.ProfileRepository
import com.abdurrahmanjun.profile.persentation.feature.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class ProfileModuleContainer : ModuleContainer() {

    private val webServiceModule = module {
        single<ProfileWebServices> {
            RetrofitProvider.retrofit().create(ProfileWebServices::class.java)
        }
    }

    private val dataSourceModule = module {
        single { ProfilerDataFactory(get()) }
    }

    private val repositoryModule = module {
        factory<ProfileRepository> { ProfileRepositoryImpl(get()) }
    }

    private val viewModelModule = module {
        viewModel { LoginViewModel(get()) }
    }
}