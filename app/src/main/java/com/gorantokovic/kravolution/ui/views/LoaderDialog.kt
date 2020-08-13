package com.gorantokovic.kravolution.ui.views

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.gorantokovic.kravolution.R

class LoaderDialog(context: Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.progress_background)
    }
}