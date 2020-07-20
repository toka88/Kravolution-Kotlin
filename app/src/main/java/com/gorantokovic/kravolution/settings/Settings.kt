package com.gorantokovic.kravolution.settings

import android.content.Context

class Settings() {
    companion object {
        private val preferenceFileName = "SETTINGS"
        private val kHasOnboardingShowed = "kHasOnboardingShowed"

        internal fun hasOnboardingShowed(context: Context): Boolean {
            var sharedPreferences =
                context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE)
            return sharedPreferences.getBoolean(kHasOnboardingShowed, false)
        }

        internal fun updateOnboardingStatus(context: Context, showed: Boolean) {
            var sharedPreferences =
                context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putBoolean(kHasOnboardingShowed, showed)
                .commit()
        }

        internal fun clearSettingsData(context: Context) {
            context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .commit()
        }
    }
}