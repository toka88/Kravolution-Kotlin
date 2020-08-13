package com.gorantokovic.kravolution.networking.interfaces

import com.gorantokovic.kravolution.models.Event
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SchedulerInterface {
    @GET("api/scheduler")
    fun getScheduler(@Query("from") fromUnixTime: String, @Query("to") toTimeStamp: String): Call<List<Event>>
}