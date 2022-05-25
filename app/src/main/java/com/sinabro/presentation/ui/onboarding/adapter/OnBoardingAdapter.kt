package com.sinabro.presentation.ui.onboarding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sinabro.presentation.ui.onboarding.OnBoardingFourFragment
import com.sinabro.presentation.ui.onboarding.OnBoardingOneFragment
import com.sinabro.presentation.ui.onboarding.OnBoardingThreeFragment
import com.sinabro.presentation.ui.onboarding.OnBoardingTwoFragment

class OnBoardingAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> OnBoardingOneFragment()
            1 -> OnBoardingTwoFragment()
            2 -> OnBoardingThreeFragment()
            else -> OnBoardingFourFragment()
        }
    }
}