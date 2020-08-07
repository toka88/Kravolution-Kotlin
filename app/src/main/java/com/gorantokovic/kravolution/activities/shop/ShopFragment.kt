package com.gorantokovic.kravolution.activities.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageButton
import com.gorantokovic.kravolution.R
import com.gorantokovic.kravolution.activities.navigation.BaseNavigationFragment
import com.gorantokovic.kravolution.activities.navigation.NavigationActivity
import kotlinx.android.synthetic.main.activity_shop.*
import kotlinx.android.synthetic.main.header.*

class ShopFragment : BaseNavigationFragment() {

    private lateinit var menuButton: ImageButton
    private lateinit var shopWebView: WebView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  = inflater.inflate(R.layout.activity_shop, container, false)
        configureViews(view)
        return view
    }

    override fun getBackgroundDrawableId(): Int {
        return R.drawable.background_primary
    }

    private fun configureViews(view: View) {
        // Web view
        shopWebView = view.findViewById(R.id.shopWebView)
        shopWebView.loadUrl("https://www.google.rs")

        // Menu button
        menuButton = view.findViewById(R.id.menuButton)
        menuButton.setOnClickListener {
            (activity as? NavigationActivity)?.openDrawer()
        }
    }
}