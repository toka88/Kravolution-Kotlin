package com.gorantokovic.kravolution.models

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
    val user: User? = null
)