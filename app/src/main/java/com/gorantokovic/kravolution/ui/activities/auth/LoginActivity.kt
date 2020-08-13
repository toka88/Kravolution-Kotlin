package com.gorantokovic.kravolution.ui.activities.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import com.gorantokovic.kravolution.R
import com.gorantokovic.kravolution.ui.activities.navigation.NavigationActivity
import com.gorantokovic.kravolution.extensions.afterTextChanged
import com.gorantokovic.kravolution.networking.InfiniteApi
import com.gorantokovic.kravolution.networking.Result
import com.gorantokovic.kravolution.ui.views.Loader
import kotlinx.android.synthetic.main.activity_login.*

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
        Loader.show(this)
        InfiniteApi.login(email, password) {
            Loader.remove()
            when (it) {
                is Result.Success -> {
                    NavigationActivity.show(this)
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
