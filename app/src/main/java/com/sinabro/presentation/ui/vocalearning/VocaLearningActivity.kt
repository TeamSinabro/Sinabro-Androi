package com.sinabro.presentation.ui.vocalearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.sinabro.R
import com.sinabro.databinding.ActivityVocaLearningBinding
import com.sinabro.databinding.ActivityVocaSearchBinding
import com.sinabro.presentation.base.BaseActivity
import com.sinabro.presentation.ui.vocalearning.viewmodel.VocaLearningViewModel
import com.sinabro.shared.util.SinabroShareData
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.viewModel

@AndroidEntryPoint
class VocaLearningActivity : BaseActivity<ActivityVocaLearningBinding>(R.layout.activity_voca_learning) {

    private val vocaLearningViewModel : VocaLearningViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
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

    //데이터 받아오기
    private fun initView(){
        val data = SinabroShareData
        vocaLearningViewModel.getVocaLearningData(data.publisher,data.subject,data.chapter)
        vocaLearningViewModel.vocaLearningData.observe(this){
            binding.vocaGetLearningData = it
        }

    }
}