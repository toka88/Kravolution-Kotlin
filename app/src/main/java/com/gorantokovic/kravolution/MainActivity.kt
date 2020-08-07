package com.gorantokovic.kravolution

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gorantokovic.kravolution.activities.auth.LoginActivity
import com.gorantokovic.kravolution.activities.home.HomeActivity
import com.gorantokovic.kravolution.activities.onboarding.OnboardingActivity
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        HomeActivity.show(this)

//        if (!Settings.hasOnboardingShowed(this)) {
//            showOnboarding()
//        } else {
//            showAuth()
//        }
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