package com.gorantokovic.kravolution.models

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    val email: String,
    val password: String,
    @SerializedName("name") val username: String? = null
)