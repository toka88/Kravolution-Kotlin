package com.gorantokovic.kravolution.networking

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

inline fun <reified T> Call<T>.enqueue(crossinline result: (Result<T>) -> Unit) {
    enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, error: Throwable) {
            Log.i("Call enqueue error", "Error: $error")
            error.localizedMessage?.let {
                val networkError = NetworkError(-1, it)
                result(Result.Failure(call, networkError))
            } ?: run {
                val networkError = NetworkError(-2, "Message is empty")
                result(Result.Failure(call, networkError))
            }
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            Log.i(
                "Call enqueue success",
                "Response isSuccessful: ${response.isSuccessful}"
            )
            if (!response.isSuccessful) {
                getErrorResponse(response)?.let {
                    result(Result.Failure(call, it))
                } ?: run {
                    val generalError = NetworkError(-3, "General error")
                    result(Result.Failure(call, generalError))
                }
                return
            }

            result(Result.Success(call, response))
        }

        private fun getErrorResponse(response: Response<T>): NetworkError? {
            val errorBody = response.errorBody()
            val errorConverter =
                RetrofitClientManager.invoke().responseBodyConverter<NetworkError>(
                    NetworkError::class.java, arrayOfNulls<Annotation>(0)
                )
            errorBody?.let {
                return errorConverter.convert(it)
            }
            return null
        }
    })
}