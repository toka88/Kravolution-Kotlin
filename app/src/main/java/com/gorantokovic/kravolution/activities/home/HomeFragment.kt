package com.gorantokovic.kravolution.activities.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.gorantokovic.kravolution.R
import com.gorantokovic.kravolution.activities.navigation.BaseNavigationFragment
import com.gorantokovic.kravolution.activities.navigation.NavigationActivity

class HomeFragment : BaseNavigationFragment() {

    private lateinit var menuButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("HomeFragment", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("HomeFragment", "onCreateView")
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        registerListeners(view)
        return view
    }

    override fun getBackgroundDrawableId(): Int {
        return R.drawable.background
    }


    private fun registerListeners(view: View) {
        menuButton = view.findViewById(R.id.menuButton)
        menuButton.setOnClickListener {
            openDrawer()
        }
    }
}