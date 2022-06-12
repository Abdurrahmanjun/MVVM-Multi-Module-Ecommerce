package com.abdurrahmanjun.profile.data.remote

import com.abdurrahmanjun.core.remote.TrolliResponse
import com.abdurrahmanjun.profile.data.remote.request.LoginRequest
import com.abdurrahmanjun.profile.data.remote.response.LoginResponse
import com.abdurrahmanjun.profile.data.remote.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProfileWebServices {

    object EndPoint {
        object User {
            const val GET_USER = "user"
            const val POST_LOGIN = "user/login"
        }
    }

    @GET(EndPoint.User.GET_USER)
    fun getUser(): TrolliResponse<UserResponse>

    @POST(EndPoint.User.POST_LOGIN)
    fun postLogin(
        @Body request: LoginRequest
    ): TrolliResponse<LoginResponse>
}