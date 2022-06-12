package com.abdurrahmanjun.profile.domain

import com.abdurrahmanjun.core.event.StateEventManager
import com.abdurrahmanjun.profile.data.remote.request.LoginRequest
import com.abdurrahmanjun.profile.domain.entity.Login
import com.abdurrahmanjun.profile.domain.entity.User
import java.io.Closeable

interface ProfileRepository : Closeable {
    val userStateEventManager: StateEventManager<User>
    val loginStateEventManager: StateEventManager<Login>

    fun getUser()
    fun login(request: LoginRequest)

    fun saveToken(token: String)
}