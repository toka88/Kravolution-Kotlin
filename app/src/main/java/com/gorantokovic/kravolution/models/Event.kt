package com.gorantokovic.kravolution.models

import com.google.gson.annotations.SerializedName

data class Event(
    var id: Int,
    var name: String? = null,
    @SerializedName("starts_at")
    var startsAt: Long,
    @SerializedName("ends_at")
    var endsAt: Long,
    var canceled: Boolean = false,
    @SerializedName("group_id")
    var groupId: Int? = null,
    @SerializedName("exam_id")
    var examId: Int? = null,
    var member: Boolean = false,
    var type: EventType? = null
)