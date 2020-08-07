package com.gorantokovic.kravolution.activities.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gorantokovic.kravolution.R
import com.gorantokovic.kravolution.activities.BaseActivity
import com.gorantokovic.kravolution.activities.home.HomeActivity
import com.gorantokovic.kravolution.activities.shop.ShopActivity
import kotlinx.android.synthetic.main.activity_navigation.*

abstract class BaseNavigationActivity : BaseActivity() {

    protected abstract fun getLayoutResourceId(): Int
    protected abstract fun getBackgroundDrawable(): Int
    protected abstract fun getMenuItemId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        configureViews()
    }

    private fun configureViews() {
        // Close button
        closeButton.setOnClickListener {
            closeDrawer()
        }

        // Menu recycler view
        menuRecyclerView.adapter = NavigationAdapter(this, { view, itemType ->
            handleMenuItemClick(itemType)
        })
        menuRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        // Backgroud
        contentRelativeLayout.background = getDrawable(getBackgroundDrawable())

        // Bottom navigation
        bottonNavigationView.selectedItemId = getMenuItemId()
        bottonNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.shop -> {
                    val intent = Intent(this, ShopActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener true
            }

        }
        // ContentFrame
        inflateView()
    }

    private fun handleMenuItemClick(itemType: NavigationAdapter.MenyItemType) {
        showToast("Clicked on $itemType")
    }

    private  fun inflateView() {
        // - inflate layout into Frame container
        val inflater =
            getSystemService(Context.LAYOUT_INFLATER_SERVICE) as? LayoutInflater
        if (inflater != null) {
            val clContent = inflater.inflate(getLayoutResourceId(), null) as ViewGroup?
            if (clContent != null) {
                containerFrameLayout.addView(clContent)
            }
        }
    }

    // Drawer

    protected fun openDrawer() {
        drawer.openDrawer(GravityCompat.START);
    }

    protected fun closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }

    // Bottom navigation

    private fun handleBottomNavigationItemClick(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == getMenuItemId()) {
            return false
        }

        when (menuItem.itemId) {
            R.id.home -> {
                startActivity(Intent(this, HomeActivity::class.java))
            }
            R.id.shop -> {
                startActivity(Intent(this, ShopActivity::class.java))
            }
        }
        return true
    }
}