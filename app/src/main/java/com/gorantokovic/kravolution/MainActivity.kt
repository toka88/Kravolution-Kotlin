package com.gorantokovic.kravolution

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gorantokovic.kravolution.activities.onboarding.OnboardingActivity
import com.gorantokovic.kravolution.settings.Settings
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Settings.clearSettingsData(this)

        if (!Settings.hasOnboardingShowed(this)) {
            showOnboarding()
        }
    }

    private fun showOnboarding() {
        Timer("OnboardingCountDown", false).schedule(3000, action = {
            val intent: Intent = Intent(this@MainActivity, OnboardingActivity::class.java)
            startActivity(intent)
        })
    }
}