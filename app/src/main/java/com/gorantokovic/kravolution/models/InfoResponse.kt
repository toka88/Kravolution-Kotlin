package com.gorantokovic.kravolution.models

import com.google.gson.annotations.SerializedName

data class InfoResponse(
    @SerializedName("info_message") val infoMessage: String
)