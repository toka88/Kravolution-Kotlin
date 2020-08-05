package com.gorantokovic.kravolution.models

import com.google.gson.annotations.SerializedName

data class ResetPasswordRequest(
    val token: String,
    val password: String,
    @SerializedName("password_confirmation") val passwordConfirmation: String
)