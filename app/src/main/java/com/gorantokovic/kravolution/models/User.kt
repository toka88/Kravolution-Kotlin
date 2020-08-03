package com.gorantokovic.kravolution.models

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    val email: String,
    @SerializedName("first_name") val firstName: String? = null,
    @SerializedName("last_name") val lastName: String? = null,
    val name: String? = null,
    val avatar: String? = null,
    val thumbnail: String? = null,
    @SerializedName("selected_channel_id") val selectedChannelId: Int? = null,
    @SerializedName("store_url") val storeUrl: String? = null
)