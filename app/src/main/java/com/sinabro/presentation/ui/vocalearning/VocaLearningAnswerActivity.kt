package com.sinabro.presentation.ui.vocalearning

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.sinabro.R
import com.sinabro.databinding.ActivityVocaLearningAnswerBinding
import com.sinabro.domain.model.response.vocalearning.VocaLearningCheckData
import com.sinabro.presentation.base.BaseActivity
import com.sinabro.presentation.ui.vocalearning.viewmodel.VocaLearningViewModel
import com.sinabro.shared.util.SinabroShareData
import dagger.hilt.android.AndroidEntryPoint
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class VocaLearningAnswerActivity : BaseActivity<ActivityVocaLearningAnswerBinding>(R.layout.activity_voca_learning_answer) {
    private val vocaLearningViewModel: VocaLearningViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goMain()
        initView()
        goNextAnswer()
        searchSentence()
        setSentence()
        divisionView()
    }


    //메인 이동
    private fun goMain(){
        binding.textVocaLearningMain.setOnClickListener {
            SinabroShareData.answerNum = 0
            finish()
        }
    }

    //검색 문장 서버 통신
    private fun searchSentence(){
        val answer = intent.getStringExtra("right") ?: ""
        vocaLearningViewModel.getVocaSearchData(answer)
    }

    //검색 문장 데이터 넣기
    private fun setSentence(){
        vocaLearningViewModel.vocaSentence.observe(this){
            binding.vocaLearningViewModel = vocaLearningViewModel
        }
    }

    //보이기 안보이기 구분
    private fun divisionView(){
        binding.textAnswerSynonym.setOnClickListener {
            vocaLearningViewModel.definitionHint = !vocaLearningViewModel.definitionHint
            binding.divisions = vocaLearningViewModel.definitionHint
        }

        binding.textAnswerSentence.setOnClickListener {
            vocaLearningViewModel.sentenceHint = !vocaLearningViewModel.sentenceHint
            binding.divisionSentence = vocaLearningViewModel.sentenceHint
        }
    }
    //뷰 체크
    private fun initView(){
        var answer = intent.getBooleanExtra("answer" , false)
        binding.divisions = vocaLearningViewModel.definitionHint
        binding.divisionSentence = vocaLearningViewModel.sentenceHint
        binding.vocaLearningCheck = VocaLearningCheckData(intent.getStringArrayExtra("definition")
            ?.toList() ?: emptyList())
        if(answer){
            binding.textAnswer.text = getString(R.string.right)
            initKonfetti()
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
             finish()
         }else{
             finish()
         }
        }
    }

    private fun initKonfetti(){
        val party = Party(
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
            position = Position.Relative(0.5, 0.3)
        )
        binding.konfetti.start(party)
    }
}