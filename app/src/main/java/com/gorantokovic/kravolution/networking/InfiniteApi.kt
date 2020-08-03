package com.gorantokovic.kravolution.networking

import com.gorantokovic.kravolution.models.AuthRequest
import com.gorantokovic.kravolution.models.AuthResponse
import com.gorantokovic.kravolution.models.RefreshTokenRequest
import com.gorantokovic.kravolution.models.User
import com.gorantokovic.kravolution.networking.interfaces.AuthInterface
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
                        val body = it.response.body()
                        body?.accessToken?.let {
                            PreferenceManager.accessToken = it
                        }
                        body?.refreshToken?.let {
                            PreferenceManager.refreshToken = it
                        }
                        body?.user?.let {
                            PreferenceManager.user = it
                        }
                    }
                }

                return@enqueue callback(it)
            }
            return call
        }

        fun register(
            username: String,
            email: String,
            password: String,
            callback: (Result<AuthResponse>) -> Unit
        ): Call<AuthResponse> {
            val body = AuthRequest(email, password)
            val call = buildService(AuthInterface::class.java).register(body)
            call.enqueue {
                when (it) {
                    is Result.Success -> {
                        val body = it.response.body()
                        body?.accessToken?.let {
                            PreferenceManager.accessToken = it
                        }
                        body?.refreshToken?.let {
                            PreferenceManager.refreshToken = it
                        }
                        body?.user?.let {
                            PreferenceManager.user = it
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

        // User

        fun getProfile(callback: (Result<User>) -> Unit): Call<User> {
            val call = buildService(UserInterface::class.java).getProfile()
            call.enqueue {
                return@enqueue callback(it)
            }
            return call
        }
    }
}