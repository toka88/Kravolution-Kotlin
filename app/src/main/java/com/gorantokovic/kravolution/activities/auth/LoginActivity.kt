package com.gorantokovic.kravolution.activities.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import com.gorantokovic.kravolution.R
import com.gorantokovic.kravolution.extensions.afterTextChanged
import com.gorantokovic.kravolution.networking.InfiniteApi
import kotlinx.android.synthetic.main.activity_login.*
import com.gorantokovic.kravolution.networking.Result

class LoginActivity : BaseAuthActivity() {

    companion object {
        internal fun show(packageContext: Context) {
            val intent = Intent(packageContext, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            packageContext.startActivity(intent)
        }
    }

    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitle(R.string.sign_in_button_title)
        registerListeners()
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_login
    }

    private fun registerListeners() {
        // Email edit text
        emailEditText.afterTextChanged {
            updateSignInButtonStatus()
        }

        // Password edit text
        passwordEditText.afterTextChanged {
            updateSignInButtonStatus()
        }

        // Sign in button
        signInButton.isEnabled = false
        signInButton.setOnClickListener {
            attemptLogin()
        }

        // Create account button
        createAccountButton.setOnClickListener {
            RegisterActivity.show(this)
        }

        // Reset password button
        resetPasswordButton.setOnClickListener {
            ForgotPasswordActivity.show(this)
        }
    }

    private fun updateSignInButtonStatus() {
        signInButton.isEnabled =
            emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()
    }

    private fun attemptLogin() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        if (email.isEmpty() || password.isEmpty()) {
            showToast("All fields are required")
        }
        InfiniteApi.login(email, password) {
            when (it) {
                is Result.Success -> {
                    Log.i("LoginActivity login", "Success: ${it.response.body()}")
                }
                is Result.Failure -> {
                    showToast(it.error.message)
                }
            }
        }
    }

    private fun showLoader() {
//        progressBar = ProgressBar()
    }
}
