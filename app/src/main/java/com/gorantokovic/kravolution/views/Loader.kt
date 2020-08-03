package com.gorantokovic.kravolution.views

import android.content.Context

object Loader {
    private var dialog: LoaderDialog? = null

    fun show(context: Context) {
        dialog?.isShowing?.let {
            remove()
        }

        dialog = LoaderDialog(context)
        dialog?.show()
    }

    fun remove() {

    }
}