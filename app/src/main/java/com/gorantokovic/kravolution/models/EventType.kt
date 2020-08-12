package com.gorantokovic.kravolution.models

import com.google.gson.annotations.SerializedName

enum class EventType {
    @SerializedName("EXAM")
    Exam,
    @SerializedName("LESSON")
    Lesson
}