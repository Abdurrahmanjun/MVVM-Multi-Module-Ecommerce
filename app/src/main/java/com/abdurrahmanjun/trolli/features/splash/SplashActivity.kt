package com.abdurrahmanjun.trolli.features.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.abdurrahmanjun.core.util.intentTo
import com.abdurrahmanjun.homepage.persentation.feature.homepage.HomePageActivity
import com.abdurrahmanjun.profile.persentation.feature.login.LoginActivity
import com.abdurrahmanjun.trolli.R
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val progressBar: ProgressBar by lazy {
        findViewById(R.id.progress_bar)
    }

    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val userManager = splashViewModel.userManager
        userManager.onLoading = {
            progressBar.isVisible = true
        }
        userManager.onSuccess = {
            progressBar.isVisible = false
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
            intentTo(HomePageActivity::class.java.name)
            finish()
        }
        userManager.onFailure = { _, _ ->
            progressBar.isVisible = false
            intentTo(LoginActivity::class.java.name)
        }

        splashViewModel.getUser()
    }
}