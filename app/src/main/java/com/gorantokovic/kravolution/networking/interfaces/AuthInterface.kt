package com.gorantokovic.kravolution.networking.interfaces

import com.gorantokovic.kravolution.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthInterface {
    @POST("api/auth/login")
    fun login(@Body body: AuthRequest): Call<AuthResponse>

    @POST("api/auth/register")
    fun register(@Body body: AuthRequest): Call<AuthResponse>

    @POST("api/auth/login/refresh")
    fun refreshToken(@Body body: RefreshTokenRequest): Call<AuthResponse>
  
    @POST("api/auth/forgotPassword")
    fun requestResetPasswordCode(@Body body: ForgotPasswordRequest): Call<InfoResponse>

    @POST("api/auth/resetPassword")
    fun resetPassword(@Body body: ResetPasswordRequest): Call<InfoResponse>
}