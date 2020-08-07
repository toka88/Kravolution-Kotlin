package com.gorantokovic.kravolution.activities.shop

import android.os.Bundle
import com.gorantokovic.kravolution.R
import com.gorantokovic.kravolution.activities.navigation.BaseNavigationActivity
import kotlinx.android.synthetic.main.activity_shop.*
import kotlinx.android.synthetic.main.header.*

class ShopActivity : BaseNavigationActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureViews()
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_shop
    }

    override fun getBackgroundDrawable(): Int {
        return R.drawable.background_primary
    }

    override fun getMenuItemId(): Int {
        return R.id.shop
    }

    private fun configureViews() {
        // Web view
        shopWebView.loadUrl("https://www.google.rs")

        // Menu button
        menuButton.setOnClickListener {
            openDrawer()
        }
    }
}