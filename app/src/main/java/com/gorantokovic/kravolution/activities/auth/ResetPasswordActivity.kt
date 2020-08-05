package com.gorantokovic.kravolution.activities.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.gorantokovic.kravolution.R
import com.gorantokovic.kravolution.extensions.afterTextChanged
import com.gorantokovic.kravolution.networking.InfiniteApi
import com.gorantokovic.kravolution.networking.Result
import com.gorantokovic.kravolution.views.Loader
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPasswordActivity : BaseAuthActivity() {
    companion object {
        internal fun show(packageContext: Context) {
            val intent = Intent(packageContext, ResetPasswordActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            packageContext.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitle(R.string.reset_password_screen_title)
        setupListeners()
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_reset_password
    }

    private fun setupListeners() {
        // Reset code edit text
        resetCodeEditText.afterTextChanged {
            updateButtonStatus()
        }

        newPasswordEditText.afterTextChanged {
            updateButtonStatus()
        }

        confirmPasswordEditText.afterTextChanged {
            updateButtonStatus()
        }

        submitButton.setOnClickListener {
            updatePassword()
        }
    }

    private fun updateButtonStatus() {
        submitButton.isEnabled =
            resetCodeEditText.text.isNotEmpty() && newPasswordEditText.text.isNotEmpty() && confirmPasswordEditText.text.isNotEmpty()
    }

    private fun updatePassword() {
        val code = resetCodeEditText.text.toString()
        val password = newPasswordEditText.text.toString()
        val confirmationPassword = confirmPasswordEditText.text.toString()

        Loader.show(this)
        InfiniteApi.resetPassword(code, password, confirmationPassword) {
            Loader.remove()
            when (it) {
                is Result.Success -> {
                    it.response?.body()?.infoMessage?.let {
                        showToast(it)
                        // TODO: Back to the root activity
                        finish()
                    }
                }
                is Result.Failure -> {
                    showToast(it.error.message)
                }
            }
        }
    }

}