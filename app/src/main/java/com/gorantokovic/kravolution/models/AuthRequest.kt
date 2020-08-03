package com.gorantokovic.kravolution.models

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    val email: String,
    val password: String,
    @SerializedName("first_name") val firstName: String? = null,
    @SerializedName("last_name") val lastName: String? = null
)