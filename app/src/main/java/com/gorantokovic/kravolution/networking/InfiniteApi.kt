package com.gorantokovic.kravolution.networking

import com.gorantokovic.kravolution.models.*
import com.gorantokovic.kravolution.networking.interfaces.AuthInterface
import com.gorantokovic.kravolution.networking.interfaces.SchedulerInterface
import com.gorantokovic.kravolution.networking.interfaces.UserInterface
import com.gorantokovic.kravolution.persistance.PreferenceManager
import retrofit2.Call

class InfiniteApi {
    companion object {
        private fun <T> buildService(service: Class<T>): T {
            return RetrofitClientManager.invoke().create(service)
        }

        // Auth

        fun login(
            email: String,
            password: String,
            callback: (Result<AuthResponse>) -> Unit
        ): Call<AuthResponse> { //5
            val body = AuthRequest(email, password)
            val call = buildService(AuthInterface::class.java)
                .login(body)

            call.enqueue {
                when (it) {
                    is Result.Success -> {
                        val authResponse = it.response.body()
                        authResponse?.accessToken?.let { accessToken ->
                            PreferenceManager.accessToken = accessToken
                        }
                        authResponse?.refreshToken?.let { refreshToken ->
                            PreferenceManager.refreshToken = refreshToken
                        }
                        authResponse?.user?.let { user ->
                            PreferenceManager.user = user
                        }
                    }
                }

                return@enqueue callback(it)
            }
            return call
        }

        fun register(
            name: String,
            email: String,
            password: String,
            callback: (Result<AuthResponse>) -> Unit
        ): Call<AuthResponse> {
            val body = AuthRequest(email, password, name)
            val call = buildService(AuthInterface::class.java).register(body)
            call.enqueue {
                when (it) {
                    is Result.Success -> {
                        val authResponse = it.response.body()
                        authResponse?.accessToken?.let { accessToken ->
                            PreferenceManager.accessToken = accessToken
                        }
                        authResponse?.refreshToken?.let { refreshToken ->
                            PreferenceManager.refreshToken = refreshToken
                        }
                        authResponse?.user?.let { user ->
                            PreferenceManager.user = user
                        }
                    }
                }
                return@enqueue callback(it)
            }
            return call
        }

        fun refreshToken(refreshToken: String): Call<AuthResponse> {
            val body = RefreshTokenRequest(refreshToken)
            return buildService(AuthInterface::class.java)
                .refreshToken(body)
        }

        fun requestResetPasswordCode(
            email: String,
            callback: (Result<InfoResponse>) -> Unit
        ): Call<InfoResponse> {
            val body = ForgotPasswordRequest(email)
            val call = buildService(AuthInterface::class.java)
                .requestResetPasswordCode(body)
            call.enqueue {
                callback(it)
            }
            return call
        }

        fun resetPassword(
            code: String,
            password: String,
            passwordConfirmation: String,
            callback: (Result<InfoResponse>) -> Unit
        ): Call<InfoResponse> {
            val body = ResetPasswordRequest(code, password, passwordConfirmation)
            val call = buildService(AuthInterface::class.java)
                .resetPassword(body)
            call.enqueue {
                callback(it)
            }
            return call
        }

        // User

        fun getProfile(callback: (Result<User>) -> Unit): Call<User> {
            val call = buildService(UserInterface::class.java).getProfile()
            call.enqueue {
                return@enqueue callback(it)
            }
            return call
        }

        // Scheduler

        fun fetchScheduler(
            from: String,
            to: String,
            callback: (Result<List<Event>>) -> Unit
        ): Call<List<Event>> {
            val call = buildService(SchedulerInterface::class.java).getScheduler(from, to)
            call.enqueue {
                return@enqueue callback(it)
            }
            return call
        }
    }
}