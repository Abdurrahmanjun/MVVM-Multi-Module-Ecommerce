package com.abdurrahmanjun.profile.data

import com.abdurrahmanjun.core.util.mapObservable
import com.abdurrahmanjun.profile.data.mapper.ProfileMapper
import com.abdurrahmanjun.profile.data.remote.ProfileWebServices
import com.abdurrahmanjun.profile.data.remote.request.LoginRequest
import com.abdurrahmanjun.profile.domain.entity.Login
import com.abdurrahmanjun.profile.domain.entity.User
import io.reactivex.Observable

class ProfilerDataFactory(private val profileWebServices: ProfileWebServices) {

    fun getUser(): Observable<User> {
        return profileWebServices.getUser().mapObservable {
            ProfileMapper.mapUserResponseToEntity(it)
        }
    }

    fun postLogin(request: LoginRequest): Observable<Login> {
        return profileWebServices.postLogin(request).mapObservable {
            ProfileMapper.mapLoginResponseToEntity(it)
        }
    }
}