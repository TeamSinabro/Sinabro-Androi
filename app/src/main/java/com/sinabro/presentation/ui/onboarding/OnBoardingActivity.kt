package com.sinabro.presentation.ui.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sinabro.R
import com.sinabro.databinding.ActivityOnBoardingBinding
import com.sinabro.presentation.base.BaseActivity
import com.sinabro.presentation.ui.onboarding.adapter.OnBoardingAdapter

class OnBoardingActivity : BaseActivity<ActivityOnBoardingBinding>(R.layout.activity_on_boarding) {
    private lateinit var onBoardingAdapter : OnBoardingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }


    //어댑터 ViewPager 연결
    private fun initView(){
        onBoardingAdapter = OnBoardingAdapter(this)
        binding.vpOnboarding.adapter = onBoardingAdapter
        binding.dotsIndicator.setViewPager2(binding.vpOnboarding)
    }
}