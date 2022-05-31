package com.sinabro.presentation.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kakao.util.helper.Utility
import com.sinabro.R
import com.sinabro.databinding.ActivityOnBoardingBinding
import com.sinabro.presentation.base.BaseActivity
import com.sinabro.presentation.ui.onboarding.adapter.OnBoardingAdapter
import com.sinabro.presentation.ui.subjectselect.SubjectSelectActivity
import timber.log.Timber

class OnBoardingActivity : BaseActivity<ActivityOnBoardingBinding>(R.layout.activity_on_boarding) {
    private lateinit var onBoardingAdapter : OnBoardingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        goSubjectSelect()
        var keyHash = Utility.getKeyHash(this)
        Timber.d("keyhash : $keyHash")
    }


    //어댑터 ViewPager 연결
    private fun initView(){
        onBoardingAdapter = OnBoardingAdapter(this)
        binding.vpOnboarding.adapter = onBoardingAdapter
        binding.dotsIndicator.setViewPager2(binding.vpOnboarding)
    }

    //출판사 선택으로 넘어가기
    private fun goSubjectSelect(){
        binding.textStart.setOnClickListener {
            val intent= Intent(this, SubjectSelectActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}