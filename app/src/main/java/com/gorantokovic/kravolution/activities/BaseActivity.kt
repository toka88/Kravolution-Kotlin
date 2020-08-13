package com.gorantokovic.kravolution.activities

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}