package com.gorantokovic.kravolution.activities.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.gorantokovic.kravolution.R
import com.gorantokovic.kravolution.activities.BaseActivity

abstract class BaseAuthActivity : BaseActivity() {
    private lateinit var titleTextView: TextView
    private lateinit var frameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_auth)

        titleTextView = findViewById(R.id.titleTextView)
        frameLayout = findViewById(R.id.frameLayout)

        inflateView()
    }

    // Interface

    protected abstract fun getLayoutResourceId(): Int

    override fun setTitle(title: CharSequence?) {
        super.setTitle(title)
        titleTextView.text = title
    }

    override fun setTitle(titleId: Int) {
        super.setTitle(titleId)
        titleTextView.text = title
    }

    private  fun inflateView() {
        // - inflate layout into Frame container
        val inflater =
            getSystemService(Context.LAYOUT_INFLATER_SERVICE) as? LayoutInflater
        if (inflater != null) {
            val clContent = inflater.inflate(getLayoutResourceId(), null) as ViewGroup?
            if (clContent != null) {
                frameLayout.addView(clContent)
            }
        }
    }
}