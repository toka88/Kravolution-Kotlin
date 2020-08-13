package com.gorantokovic.kravolution.networking

import com.gorantokovic.kravolution.persistance.PreferenceManager
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        PreferenceManager.refreshToken?.let {
            val res = InfiniteApi.refreshToken(it).execute()
            if (res.isSuccessful) {
                PreferenceManager.accessToken = res.body()?.accessToken ?: null
                PreferenceManager.refreshToken = res.body()?.refreshToken ?: null

                PreferenceManager.accessToken?.let { accessToken ->
                    return response.request()
                        .newBuilder()
                        .header("Authorization", "Bearer $accessToken")
                        .build()
                }
            }
            logout()
            return null
        }
        logout()
        return null
    }

    private fun logout() {
        // TODO: Implement logout
    }

}