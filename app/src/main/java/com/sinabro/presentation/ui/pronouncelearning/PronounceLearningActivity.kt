package com.sinabro.presentation.ui.pronouncelearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sinabro.R
import com.sinabro.databinding.ActivityPronounceLearningBinding
import com.sinabro.presentation.base.BaseActivity

class PronounceLearningActivity : BaseActivity<ActivityPronounceLearningBinding>(R.layout.activity_pronounce_learning) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goAnswer()
    }

    //사용자 점수 확인
    private fun goAnswer(){
        binding.textPronounceLearningRec.setOnClickListener {
            val intent= Intent(this, PronounceLearningAnswerActivity::class.java)
            startActivity(intent)
        }

    }
}