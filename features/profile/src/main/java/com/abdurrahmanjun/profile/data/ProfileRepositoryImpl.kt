package com.abdurrahmanjun.profile.data

import com.abdurrahmanjun.core.event.MutableStateEventManager
import com.abdurrahmanjun.core.event.StateEventManager
import com.abdurrahmanjun.core.local.DataPreferences
import com.abdurrahmanjun.core.util.fetchStateEventSubscriber
import com.abdurrahmanjun.profile.data.remote.request.LoginRequest
import com.abdurrahmanjun.profile.domain.ProfileRepository
import com.abdurrahmanjun.profile.domain.entity.Login
import com.abdurrahmanjun.profile.domain.entity.User
import io.reactivex.disposables.CompositeDisposable
import okhttp3.internal.closeQuietly

class ProfileRepositoryImpl(private val dataFactory: ProfilerDataFactory) : ProfileRepository {

    private val disposables = CompositeDisposable()

    private var _userStateEventManager: MutableStateEventManager<User> = MutableStateEventManager()
    override val userStateEventManager: StateEventManager<User>
        get() = _userStateEventManager

    private var _loginStateEventManager: MutableStateEventManager<Login> = MutableStateEventManager()
    override val loginStateEventManager: StateEventManager<Login>
        get() = _loginStateEventManager

    override fun getUser() {
        val disposable = dataFactory.getUser().fetchStateEventSubscriber { stateEvent ->
            _userStateEventManager.post(stateEvent)
        }
        disposables.add(disposable)
    }

    override fun login(request: LoginRequest) {
        val disposable = dataFactory.postLogin(request).fetchStateEventSubscriber { stateEvent ->
            _loginStateEventManager.post(stateEvent)
        }
        disposables.add(disposable)
    }

    override fun saveToken(token: String) {
        DataPreferences.get.token = token
    }


    override fun close() {
        _userStateEventManager.closeQuietly()
        _loginStateEventManager.closeQuietly()
        disposables.dispose()
    }
}