package com.abdurrahmanjun.profile.persentation.feature.login

import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.abdurrahmanjun.core.util.intentTo
import com.abdurrahmanjun.profile.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null
    private val binding: ActivityLoginBinding by lazy {
        requireNotNull(_binding)
    }

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginEventManager = loginViewModel.loginEventManager

        with(binding) {
            profileTvSignup.text = Html.fromHtml("<p>Belum punya akun? <b><u>Daftar</u></b></p>")
            profileLoginProgressbar.isVisible = false
            loginEventManager.onLoading = {
                profileLoginProgressbar.isVisible = true
                profileBtnLogin.isEnabled = false
            }
            loginEventManager.onSuccess = {
                profileLoginProgressbar.isVisible = false
                profileBtnLogin.isEnabled = true
                loginViewModel.saveToken(it)
                intentTo("com.utsman.trolliaej.MainActivity")
            }
            loginEventManager.onFailure = { code, ex ->
                profileLoginProgressbar.isVisible = false
                profileBtnLogin.isEnabled = true
            }

            profileBtnLogin.setOnClickListener {
                val username = profileEtEmail.text
                val password = profileEtPassword.text
                loginViewModel.requestLogin(username, password)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}