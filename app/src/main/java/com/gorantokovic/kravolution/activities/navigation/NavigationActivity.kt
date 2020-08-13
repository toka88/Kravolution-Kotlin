package com.gorantokovic.kravolution.activities.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gorantokovic.kravolution.MainActivity
import com.gorantokovic.kravolution.R
import com.gorantokovic.kravolution.activities.BaseActivity
import com.gorantokovic.kravolution.activities.home.HomeFragment
import com.gorantokovic.kravolution.activities.scheduler.SchedulerFragment
import com.gorantokovic.kravolution.activities.shop.ShopFragment
import com.gorantokovic.kravolution.persistance.PreferenceManager
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : BaseActivity() {

    companion object {
        internal fun show(packageContext: Context) {
            val intent = Intent(packageContext, NavigationActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            packageContext.startActivity(intent)
        }
    }

    private lateinit var mHomeFragment: HomeFragment
    private lateinit var mShopFragment: ShopFragment
    private lateinit var mSchedulerFragment: SchedulerFragment
    private lateinit var activeFragment: Fragment
    private val fragmentManager = supportFragmentManager


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
        menuRecyclerView.adapter = NavigationAdapter(this) { itemType ->
            handleMenuItemClick(itemType)
        }
        menuRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        // Bottom navigation
        fragmentManager.beginTransaction().apply {
            add(R.id.containerFrameLayout, getHomeFragment())
            add(R.id.containerFrameLayout, getShopFragment()).hide(getShopFragment())
            add(R.id.containerFrameLayout, getSchedulerFragment()).hide(getSchedulerFragment())
        }.commit()

        activeFragment = getHomeFragment()
        showFragment(getHomeFragment())
        bottomNavigationView.selectedItemId = R.id.home
        bottomNavigationView.setOnNavigationItemSelectedListener {
            return@setOnNavigationItemSelectedListener handleBottomNavigationItemClick(it)
        }
    }

    private fun handleMenuItemClick(itemType: NavigationAdapter.MenuItemType) {
        when (itemType) {
            NavigationAdapter.MenuItemType.SignOut -> {
                PreferenceManager.clearUsersData()
                MainActivity.show(this)
            }
            else -> {}
        }
        showToast("Clicked on $itemType")
    }

    // Drawer

    internal fun openDrawer() {
        drawer.openDrawer(GravityCompat.START)
    }

    internal fun closeDrawer() {
        drawer.closeDrawer(GravityCompat.START)
    }

    // Bottom navigation

    private fun showFragment(fragment: BaseNavigationFragment) {
        // Show fragment
        supportFragmentManager
            .beginTransaction()
            .hide(activeFragment)
            .show(fragment)
            .commit()

        activeFragment = fragment

        // Update background
        contentRelativeLayout.background = getDrawable(fragment.getBackgroundDrawableId())
    }

    private fun handleBottomNavigationItemClick(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.home -> {
                showFragment(getHomeFragment())
            }
            R.id.shop -> {
                showFragment(getShopFragment())
            }
            R.id.scheduler -> {
                showFragment(getSchedulerFragment())
            }
        }
        return true
    }

    // Helpers

    private fun getHomeFragment(): HomeFragment {
        if (!this::mHomeFragment.isInitialized) {
            mHomeFragment = HomeFragment()
            Log.i("NavigationActivity", "HomeFragment initialized")
        }
        return mHomeFragment
    }

    private fun getShopFragment(): ShopFragment {
        if (!this::mShopFragment.isInitialized) {
            mShopFragment = ShopFragment()
            Log.i("NavigationActivity", "ShopFragment initialized")
        }
        return mShopFragment
    }

    private fun getSchedulerFragment(): SchedulerFragment {
        if (!this::mSchedulerFragment.isInitialized) {
            mSchedulerFragment = SchedulerFragment()
            Log.i("NavigationActivity", "SchedulerFragment initialized")
        }
        return mSchedulerFragment
    }
}