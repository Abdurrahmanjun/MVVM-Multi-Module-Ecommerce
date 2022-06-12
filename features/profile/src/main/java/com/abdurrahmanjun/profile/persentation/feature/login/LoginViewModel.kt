package com.abdurrahmanjun.profile.persentation.feature.login

import androidx.lifecycle.ViewModel
import com.abdurrahmanjun.core.event.StateEventManager
import com.abdurrahmanjun.profile.data.remote.request.LoginRequest
import com.abdurrahmanjun.profile.domain.ProfileRepository
import com.abdurrahmanjun.profile.domain.entity.Login

class LoginViewModel(private val repository: ProfileRepository) : ViewModel() {
    val loginEventManager: StateEventManager<Login> = repository.loginStateEventManager

    fun requestLogin(username: String, password: String) {
        val request = LoginRequest(username, password)
        repository.login(request)
    }

    fun saveToken(login: Login) {
        repository.saveToken(login.token)
    }
}