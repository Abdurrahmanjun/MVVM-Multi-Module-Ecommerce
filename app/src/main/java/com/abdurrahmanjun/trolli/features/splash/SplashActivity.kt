package com.abdurrahmanjun.trolli.features.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.abdurrahmanjun.core.util.intentTo
import com.abdurrahmanjun.homepage.persentation.feature.homepage.HomePageActivity
import com.abdurrahmanjun.profile.databinding.ActivityLoginBinding
import com.abdurrahmanjun.profile.persentation.feature.login.LoginActivity
import com.abdurrahmanjun.trolli.R
import com.abdurrahmanjun.trolli.databinding.ActivitySplashBinding
import com.abdurrahmanjun.trolli.features.onboarding.OnBoardingActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null
    private val binding: ActivitySplashBinding by lazy {
        requireNotNull(_binding)
    }

    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userManager = splashViewModel.userManager

        with(binding) {
            userManager.onLoading = {
                progressBar.isVisible = true
            }
            userManager.onSuccess = {
                progressBar.isVisible = false
                Toast.makeText(baseContext, "success", Toast.LENGTH_SHORT).show()
                intentTo(HomePageActivity::class.java.name)
                finish()
            }
            userManager.onFailure = { _, _ ->
                progressBar.isVisible = false
                // intentTo(LoginActivity::class.java.name)
                intentTo(OnBoardingActivity::class.java.name)
            }
        }

        splashViewModel.getUser()
    }
}