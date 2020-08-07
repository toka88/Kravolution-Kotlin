package com.gorantokovic.kravolution.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClientManager {
    companion object {
        private const val PRODUCTION_URL = "https://www.infinite.coach/"
        private const val DEVELOPMENT_URL = "http://infinitykm.herokuapp.com/"

        operator fun invoke(): Retrofit {
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(HttpInterceptor())
                .authenticator(TokenAuthenticator())
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(DEVELOPMENT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
    }
}