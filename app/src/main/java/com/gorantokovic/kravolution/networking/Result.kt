package com.gorantokovic.kravolution.networking

import retrofit2.Call
import retrofit2.Response

sealed class Result<T> {
    data class Success<T>(val call: Call<T>, val response: Response<T>) : Result<T>()
    data class Failure<T>(val call: Call<T>, val error: NetworkError) : Result<T>()
}