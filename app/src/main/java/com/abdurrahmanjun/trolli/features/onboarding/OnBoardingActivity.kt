package com.abdurrahmanjun.trolli.features.onboarding

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.abdurrahmanjun.core.util.intentTo
import com.abdurrahmanjun.profile.persentation.feature.login.LoginActivity
import com.abdurrahmanjun.trolli.R
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroCustomLayoutFragment

class OnBoardingActivity : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        isSkipButtonEnabled = false
        setBarColor(Color.GRAY)

        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.fragment_onboarding_page_1))
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.fragment_onboarding_page_2))
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.fragment_onboarding_page_3))
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        intentTo(LoginActivity::class.java.name)
        finish()
    }
}