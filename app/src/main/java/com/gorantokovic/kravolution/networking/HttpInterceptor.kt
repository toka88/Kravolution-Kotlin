package com.gorantokovic.kravolution.networking

import android.util.Log
import com.gorantokovic.kravolution.persistance.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Response

class HttpInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val ongoing = chain.request().newBuilder()

        // Headers
        ongoing.addHeader("Content-Type", "application/json")
        ongoing.addHeader("Accept", "application/json")
        PreferenceManager.accessToken?.let {
            ongoing.addHeader("Authorization", "Bearer $it")
        }

        val response = chain.proceed(ongoing.build())
        val responseCode = response.code()
        Log.d("Interceptor", "Code: $responseCode")

        return response
    }
}