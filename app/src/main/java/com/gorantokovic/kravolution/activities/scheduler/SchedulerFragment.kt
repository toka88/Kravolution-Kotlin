package com.gorantokovic.kravolution.activities.scheduler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.gorantokovic.kravolution.R
import com.gorantokovic.kravolution.activities.navigation.BaseNavigationFragment
import kotlinx.android.synthetic.main.header.*

class SchedulerFragment : BaseNavigationFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_scheduler, container, false)
        customizeView(view)
        return view
    }

    override fun getBackgroundDrawableId(): Int {
        return R.drawable.background_primary
    }

    private fun customizeView(view: View) {
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        titleTextView.text = getString(R.string.scheduler_screen_title)

        val menuButton: ImageButton = view.findViewById(R.id.menuButton)
        menuButton.setOnClickListener {
            openDrawer()
        }
    }
}