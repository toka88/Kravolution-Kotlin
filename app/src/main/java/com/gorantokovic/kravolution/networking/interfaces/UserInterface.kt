package com.gorantokovic.kravolution.networking.interfaces

import com.gorantokovic.kravolution.models.User
import retrofit2.Call
import retrofit2.http.GET

interface UserInterface {
    @GET("api/user")
    fun getProfile(): Call<User>
}