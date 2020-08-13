package com.gorantokovic.kravolution.activities

import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    protected fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}