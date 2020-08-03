package com.gorantokovic.kravolution.settings

import android.content.Context

class Settings {
    companion object {
        private const val preferenceFileName = "settings"
        private const val kHasOnboardingShowed = "has_onboarding_showed"

        internal fun hasOnboardingShowed(context: Context): Boolean {
            return context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE)
                .getBoolean(kHasOnboardingShowed, false)
        }

        internal fun updateOnboardingStatus(context: Context, showed: Boolean) {
            context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(kHasOnboardingShowed, showed)
                .apply()
        }

        internal fun clearSettingsData(context: Context) {
            context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply()
        }
    }
}