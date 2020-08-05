package com.gorantokovic.kravolution.views

import android.content.Context

object Loader {
    private var dialog: LoaderDialog? = null

    fun show(context: Context) {
        dialog?.isShowing?.let {
            return
        }

        dialog = LoaderDialog(context)
        dialog?.show()
    }

    fun remove() {
        dialog?.dismiss()
        dialog = null
    }

    fun isShowing(): Boolean {
        return dialog?.isShowing ?: false
    }
}