package com.gorantokovic.kravolution.activities.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.gorantokovic.kravolution.R
import com.gorantokovic.kravolution.extensions.afterTextChanged
import com.gorantokovic.kravolution.networking.InfiniteApi
import com.gorantokovic.kravolution.networking.Result
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseAuthActivity() {
    companion object {
        internal fun show(packageContext: Context) {
            val intent = Intent(packageContext, RegisterActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            packageContext.startActivity(intent)
        }
    }

    private var termsAccepted: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitle(R.string.sign_up_button_title)
        setupListeners()
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_register
    }

    private fun setupListeners() {
        // Username edit text
        userNameEditText.afterTextChanged {
            updateSignUpButtonStatus()
        }

        // Email edit text
        emailEditText.afterTextChanged {
            updateSignUpButtonStatus()
        }

        // Password edit text
        passwordEditText.afterTextChanged {
            updateSignUpButtonStatus()
        }

        // Accept button
        acceptButton.setOnClickListener {
            termsAccepted = !termsAccepted
            acceptButton.isChecked = termsAccepted
            updateSignUpButtonStatus()
        }

        // Sign uo button
        signUpButton.text = getString(R.string.sign_up_button_title)
        signUpButton.setOnClickListener {
            showToast("Register Button tapped")
            attemptRegister()
        }
    }

    private fun updateSignUpButtonStatus() {
        signUpButton.isEnabled =
            userNameEditText.text.isNotEmpty() && emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty() && termsAccepted
    }

    private fun attemptRegister() {
        val username = userNameEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        InfiniteApi.register(username, email, password) {
            when (it) {
                is Result.Success -> {
                    // TODO: show home
                }
                is Result.Failure -> {
                    showToast(it.error.message)
                }
            }
        }
    }
}