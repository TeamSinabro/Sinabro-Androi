package com.sinabro.presentation.ui.vocalearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sinabro.R
import com.sinabro.databinding.ActivityVocaLearningAnswerBinding
import com.sinabro.domain.model.response.vocalearning.VocaLearningCheckData
import com.sinabro.presentation.base.BaseActivity
import com.sinabro.shared.util.SinabroShareData

class VocaLearningAnswerActivity : BaseActivity<ActivityVocaLearningAnswerBinding>(R.layout.activity_voca_learning_answer) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goMain()
        initView()
        goNextAnswer()
    }


    //메인 이동
    private fun goMain(){
        binding.textVocaLearningMain.setOnClickListener {
            SinabroShareData.answerNum = 0
            finish()
        }
    }
    //뷰 체크
    private fun initView(){
        var answer = intent.getBooleanExtra("answer" , false)
        binding.vocaLearningCheck = VocaLearningCheckData(intent.getStringArrayExtra("definition")
            ?.toList() ?: emptyList())
        if(answer){
            binding.textAnswer.text = getString(R.string.right)
        }else{
            binding.textAnswer.text = getString(R.string.fail)
        }
    }

    //다음 문제 이동
    private fun goNextAnswer(){
        binding.textVocaLearningNext.setOnClickListener {
         if(SinabroShareData.answerNum !=6){
             val intent = Intent(this, VocaLearningActivity::class.java)
             startActivity(intent)
             SinabroShareData.answerNum += 1
         }else{
             finish()
         }


        }
    }
}