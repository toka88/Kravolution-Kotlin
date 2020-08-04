package com.gorantokovic.kravolution.activities.navigation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gorantokovic.kravolution.R
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity() {
    companion object {
        internal fun show(packageContext: Context) {
            val intent = Intent(packageContext, NavigationActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            packageContext.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        configureViews()
    }

    private fun configureViews() {
        // Close button
        closeButton.setOnClickListener {
//            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
//            } else {
//                drawer.openDrawer(GravityCompat.START);
//            }
        }

        // Recycler view
        menuRecyclerView.adapter = NavigationAdapter(this, { view, itemType ->
            handleMenuItemClick(itemType)
        })
        menuRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun closeMenu() {
        Toast.makeText(this, "Close button tapped", Toast.LENGTH_SHORT).show()
    }

    private fun handleMenuItemClick(itemType: NavigationAdapter.MenyItemType) {
        Log.i("NavigationActivity handleMenuItemClick", "Clicked on $itemType")
    }
}