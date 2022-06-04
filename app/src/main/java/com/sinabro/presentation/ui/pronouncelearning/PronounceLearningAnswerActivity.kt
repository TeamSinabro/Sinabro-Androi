package com.sinabro.presentation.ui.pronouncelearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sinabro.R
import com.sinabro.databinding.ActivityPronounceLearningAnswerBinding
import com.sinabro.databinding.ActivityPronounceLearningBinding
import com.sinabro.presentation.base.BaseActivity
import com.sinabro.shared.util.SinabroShareData
import timber.log.Timber

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
        var sttData = intent.getStringExtra("sttData").toString()
        var problem = intent.getStringExtra("pronounceSentence").toString()

        val regex = Regex("[^가-힣]")
        problem = regex.replace(problem, " ")
        sttData = regex.replace(sttData, " ")
        Timber.d("sttDataProblem $problem")
        Timber.d("sttData $sttData")
        dNum = if("$sttData " == problem){
            Timber.d("sttData와 동일")
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