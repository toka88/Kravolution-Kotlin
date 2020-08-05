package com.gorantokovic.kravolution.activities.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.gorantokovic.kravolution.R
import com.gorantokovic.kravolution.extensions.afterTextChanged
import com.gorantokovic.kravolution.networking.InfiniteApi
import com.gorantokovic.kravolution.networking.Result
import com.gorantokovic.kravolution.views.Loader
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_forgot_password.emailEditText
import kotlinx.android.synthetic.main.activity_forgot_password.resetPasswordButton

class ForgotPasswordActivity : BaseAuthActivity() {
    companion object {
        internal fun show(packageContext: Context) {
            val intent = Intent(packageContext, ForgotPasswordActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            packageContext.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.forgot_password_screen_title)
        registerListeners()
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_forgot_password
    }

    private fun registerListeners() {
        // Email edit text
        emailEditText.afterTextChanged {
            resetPasswordButton.isEnabled = emailEditText.text.isNotEmpty()
        }

        // Reset password button
        resetPasswordButton.isEnabled = false
        resetPasswordButton.setOnClickListener {
            requestResetCode()
        }

        // Already have a code button
        alreadyHaveCodeButton.setOnClickListener {
            ResetPasswordActivity.show(this)
        }
    }

    private fun requestResetCode() {
        val email = emailEditText.text.toString()
        Loader.show(this)
        InfiniteApi.requestResetPasswordCode(email) {
            Loader.remove()
            when (it) {
                is Result.Success -> {
                    it.response.body()?.infoMessage?.let {
                        showToast(it)
                    }
                    ResetPasswordActivity.show(this)
                }
                is Result.Failure -> {
                    showToast(it.error.message)
                }
            }
        }
    }
}