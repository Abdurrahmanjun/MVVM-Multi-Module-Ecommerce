package com.abdurrahmanjun.trolli.features.splash

import androidx.lifecycle.ViewModel
import com.abdurrahmanjun.core.event.StateEventManager
import com.abdurrahmanjun.profile.domain.ProfileRepository
import com.abdurrahmanjun.profile.domain.entity.User
import okhttp3.internal.closeQuietly

class SplashViewModel(private val profileRepository: ProfileRepository) : ViewModel() {
    val userManager: StateEventManager<User> = profileRepository.userStateEventManager

    fun getUser() {
        profileRepository.getUser()
    }

    override fun onCleared() {
        super.onCleared()
        profileRepository.close()
        userManager.closeQuietly()
    }
}