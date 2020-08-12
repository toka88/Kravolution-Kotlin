package com.gorantokovic.kravolution


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gorantokovic.kravolution.activities.auth.LoginActivity
import com.gorantokovic.kravolution.activities.navigation.NavigationActivity
import com.gorantokovic.kravolution.activities.onboarding.OnboardingActivity
import com.gorantokovic.kravolution.persistance.PreferenceManager
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    companion object {
        internal fun show(packageContext: Context) {
            val intent = Intent(packageContext, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            packageContext.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (PreferenceManager.firstTimeRun) {
            showOnboarding()
        } else if (PreferenceManager.hasAuthenticated()) {
            showDashboard()
        } else {
            showAuth()
        }
    }

    private fun showDashboard() {
        NavigationActivity.show(this)
    }

    private fun showOnboarding() {
        Timer("OnboardingCountDown", false).schedule(3000, action = {
            val intent = Intent(this@MainActivity, OnboardingActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        })
    }

    private fun showAuth() {
        LoginActivity.show(this)
    }
}