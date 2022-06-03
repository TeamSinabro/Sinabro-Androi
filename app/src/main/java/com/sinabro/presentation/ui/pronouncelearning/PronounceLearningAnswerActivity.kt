package com.sinabro.presentation.ui.pronouncelearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sinabro.R
import com.sinabro.databinding.ActivityPronounceLearningAnswerBinding
import com.sinabro.databinding.ActivityPronounceLearningBinding
import com.sinabro.presentation.base.BaseActivity
import com.sinabro.shared.util.SinabroShareData

class PronounceLearningAnswerActivity : BaseActivity<ActivityPronounceLearningAnswerBinding>(R.layout.activity_pronounce_learning_answer) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goMain()
        initScore()
        nextAnswer()
    }

    //메인으로 이동
    private fun goMain(){
        binding.textPronounceLearningMain.setOnClickListener {
            finish()
        }
    }
    //점수 받아오기
    private fun initScore(){
        var dNum : Double = 0.0
        val score = intent.getStringExtra("pronounceScore")?.toDouble()
        val sttData = intent.getStringExtra("sttData")
        val problem = intent.getStringExtra("pronounceSentence")
        dNum = if(sttData == problem){
            100.0
        }else{
            ((score!!.times(20)))
        }
        with(binding){
            textPronounceLearningScore.text = String.format("%.2f", dNum)
            textPronounceSttData.text = sttData
        }



    }
    //다음문제
    private fun nextAnswer(){
        binding.textPronounceLearningNext.setOnClickListener {
                val intent = Intent(this, PronounceLearningActivity::class.java)
                startActivity(intent)
                finish()
        }
    }
}