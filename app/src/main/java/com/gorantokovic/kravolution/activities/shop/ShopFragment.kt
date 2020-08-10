package com.gorantokovic.kravolution.activities.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageButton
import android.widget.TextView
import com.gorantokovic.kravolution.R
import com.gorantokovic.kravolution.activities.navigation.BaseNavigationFragment
import com.gorantokovic.kravolution.activities.navigation.NavigationActivity

class ShopFragment : BaseNavigationFragment() {

    private lateinit var menuButton: ImageButton
    private lateinit var shopWebView: WebView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  = inflater.inflate(R.layout.fragment_shop, container, false)
        configureViews(view)
        return view
    }

    override fun getBackgroundDrawableId(): Int {
        return R.drawable.background_primary
    }

    private fun configureViews(view: View) {
        // Header
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        titleTextView.text = getString(R.string.shop_for_kit_screen_title)

        // Web view
        shopWebView = view.findViewById(R.id.shopWebView)
        shopWebView.loadUrl("https://www.google.rs")

        // Menu button
        menuButton = view.findViewById(R.id.menuButton)
        menuButton.setOnClickListener {
            openDrawer()
        }
    }
}