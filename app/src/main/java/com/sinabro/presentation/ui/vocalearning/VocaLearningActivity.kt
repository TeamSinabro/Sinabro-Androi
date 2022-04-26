package com.sinabro.presentation.ui.vocalearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sinabro.R
import com.sinabro.databinding.ActivityVocaLearningBinding
import com.sinabro.databinding.ActivityVocaSearchBinding
import com.sinabro.presentation.base.BaseActivity

class VocaLearningActivity : BaseActivity<ActivityVocaLearningBinding>(R.layout.activity_voca_learning) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goAnswer()
    }

    //정답 확인
    private fun goAnswer(){
        binding.textVocaLearningOk.setOnClickListener {
            val intent = Intent(this, VocaLearningAnswerActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}