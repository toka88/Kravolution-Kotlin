package com.gorantokovic.kravolution.persistance

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.gorantokovic.kravolution.BaseApp
import com.gorantokovic.kravolution.models.User

class PreferenceManager {
    private val mSharedPreferences: SharedPreferences =
        BaseApp.getInstance().getSharedPreferences("preferences", Context.MODE_PRIVATE)

    companion object {
        var accessToken: String?
            get() {
                return PreferenceManager().mSharedPreferences
                    .getString("access_token", null)
            }
            set(value) {
                PreferenceManager().mSharedPreferences
                    .edit()
                    .putString("access_token", value)
                    .apply()
            }

        var refreshToken: String?
            get() {
                return PreferenceManager().mSharedPreferences
                    .getString("refresh_token", null)
            }
            set(value) {
                PreferenceManager().mSharedPreferences
                    .edit()
                    .putString("refresh_token", value)
                    .apply()
            }

        var user: User?
            get() {
                return PreferenceManager.get("user")
            }
            set(value) {
                PreferenceManager.put(value, "user")
            }

        fun clearUsersData() {
            PreferenceManager().mSharedPreferences
                .edit()
                .remove("access_token")
                .remove("refresh_token")
                .apply()
        }

        fun clearAllData() {
            PreferenceManager().mSharedPreferences
                .edit()
                .clear()
                .apply()
        }

        /**
         * Saves object into the Preferences.
         *
         * @param `object` Object of model class (of type [T]) to save
         * @param key Key with which Shared preferences to
         **/
        private fun <T> put(`object`: T, key: String) {
            //Convert object to JSON String.
            val jsonString = GsonBuilder().create().toJson(`object`)
            //Save that String in SharedPreferences
            PreferenceManager().mSharedPreferences
                .edit()
                .putString(key, jsonString)
                .apply()
        }

        /**
         * Used to retrieve object from the Preferences.
         *
         * @param key Shared Preference key with which object was saved.
         **/
        private inline fun <reified T> get(key: String): T? {
            //We read JSON String which was saved.
            val value = PreferenceManager().mSharedPreferences
                .getString(key, null)
            //JSON String was found which means object can be read.
            //We convert this JSON String to model object. Parameter "c" (of
            //type Class < T >" is used to cast.
            return GsonBuilder().create()
                .fromJson(value, T::class.java)
        }
    }
}