package com.sinabro.presentation.ui.vocalearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sinabro.R
import com.sinabro.databinding.ActivityVocaLearningAnswerBinding
import com.sinabro.presentation.base.BaseActivity

class VocaLearningAnswerActivity : BaseActivity<ActivityVocaLearningAnswerBinding>(R.layout.activity_voca_learning_answer) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goMain()
    }


    //메인 이동
    private fun goMain(){
        binding.textVocaLearningMain.setOnClickListener {
            finish()
        }
    }

    //정답 확인

}