package com.gorantokovic.kravolution.activities.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import com.gorantokovic.kravolution.R
import com.gorantokovic.kravolution.activities.auth.LoginActivity
import com.gorantokovic.kravolution.persistance.PreferenceManager
import com.gorantokovic.kravolution.settings.Settings

class OnboardingActivity : AppCompatActivity() {

    private lateinit var sliderViewPager: ViewPager2
    private lateinit var dotsLayout: LinearLayout
    private lateinit var dots: ArrayList<ImageView>
    private lateinit var leftButton: Button
    private lateinit var rightButton: Button
    private lateinit var adapter: OnboardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        adapter = OnboardingAdapter()
        sliderViewPager = findViewById(R.id.sliderViewPager)
        sliderViewPager.adapter = adapter
        sliderViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == 0) {
                    leftButton.visibility = View.INVISIBLE
                } else {
                    leftButton.visibility = View.VISIBLE
                }
                updateIndicator()
            }
        })

        dotsLayout = findViewById(R.id.dotsLayout)

        leftButton = findViewById(R.id.leftButton)
        leftButton.setOnClickListener {
            sliderViewPager.setCurrentItem(sliderViewPager.currentItem - 1, true)
        }

        rightButton = findViewById(R.id.rightButton)
        rightButton.setOnClickListener {
            if (sliderViewPager.currentItem != adapter.itemCount - 1) {
                sliderViewPager.setCurrentItem(sliderViewPager.currentItem + 1, true)
            } else {
                Settings.updateOnboardingStatus(this,true)
                PreferenceManager.firstTimeRun = false
                showAuth()
            }
        }

        addDotsIndicator()
    }

    private fun addDotsIndicator() {
        dots = arrayListOf()
        val dotNumber = sliderViewPager.adapter?.itemCount ?: 0
        for (i in 0 until dotNumber) {
            val imageView = ImageView(this)
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(8,0,8,0)
            dotsLayout.addView(imageView, params)
            dots.add(imageView)
        }
        updateIndicator()
    }

    private fun updateIndicator() {
        val currentIndex = sliderViewPager.currentItem
        dots.forEachIndexed { index, imageView ->
            if (currentIndex ==  index) {
                imageView.setImageDrawable(getDrawable(R.drawable.shape_dot))
            } else {
                imageView.setImageDrawable(getDrawable(R.drawable.shape_empty_dot))
            }
        }
    }

    private fun showAuth() {
        LoginActivity.show(this)
    }
}