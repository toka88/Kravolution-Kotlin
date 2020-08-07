package com.gorantokovic.kravolution.activities.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.gorantokovic.kravolution.R
import com.gorantokovic.kravolution.activities.navigation.BaseNavigationActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_navigation.*

class HomeActivity : BaseNavigationActivity() {
    companion object {
        internal fun show(packageContext: Context) {
            val intent = Intent(packageContext, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            packageContext.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerListeners()
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_home
    }

    override fun getBackgroundDrawable(): Int {
        return R.drawable.background
    }

    override fun getMenuItemId(): Int {
        return R.id.home
    }

    private fun registerListeners() {
        menuButton.setOnClickListener {
            openDrawer()
        }
    }
}