package com.sinabro.presentation.ui.listenlearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sinabro.R
import com.sinabro.databinding.ActivityListenLearningAnswerBinding
import com.sinabro.presentation.base.BaseActivity

class ListenLearningAnswerActivity : BaseActivity<ActivityListenLearningAnswerBinding>(R.layout.activity_listen_learning_answer) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        checkEvent()
    }


    //뷰 및 데이터 연결
    private fun initView(){
        val answer = intent.getStringExtra("answer")
        val userAnswer = intent.getStringExtra("userAnswer")
        binding.textListenUserAnswer.text = userAnswer
        binding.textListenAnswer.text = answer
    }

    //클릭 이벤트
    private fun checkEvent(){
        binding.textListenLearningMain.setOnClickListener {
            finish()
        }
        binding.textListenLearningNext.setOnClickListener {
            val intent = Intent(this, ListenLearningActivity::class.java)
            startActivity(intent)
            finish()

        }

    }
}