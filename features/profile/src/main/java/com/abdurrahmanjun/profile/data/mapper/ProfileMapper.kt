package com.abdurrahmanjun.profile.data.mapper

import com.abdurrahmanjun.profile.data.remote.response.LoginResponse
import com.abdurrahmanjun.profile.data.remote.response.UserResponse
import com.abdurrahmanjun.profile.domain.entity.Login
import com.abdurrahmanjun.profile.domain.entity.User

object ProfileMapper {

    fun mapUserResponseToEntity(userResponse: UserResponse?): User {
        return User(
            city = userResponse?.city.orEmpty(),
            fullName = userResponse?.fullName.orEmpty(),
            id = userResponse?.id.orEmpty(),
            imageUrl = userResponse?.imageUrl.orEmpty(),
            role = userResponse?.role.orEmpty(),
            simpleAddress = userResponse?.simpleAddress.orEmpty(),
            username = userResponse?.username.orEmpty()
        )
    }

    fun mapLoginResponseToEntity(loginResponse: LoginResponse?): Login {
        return Login(token = loginResponse?.token.orEmpty())
    }
}