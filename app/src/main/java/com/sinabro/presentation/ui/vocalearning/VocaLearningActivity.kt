package com.sinabro.presentation.ui.vocalearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import com.sinabro.R
import com.sinabro.databinding.ActivityVocaLearningBinding
import com.sinabro.databinding.ActivityVocaSearchBinding
import com.sinabro.presentation.base.BaseActivity
import com.sinabro.presentation.base.LoadedViewModel
import com.sinabro.presentation.ui.vocalearning.adapter.VocaLearningAdapter
import com.sinabro.presentation.ui.vocalearning.viewmodel.VocaLearningViewModel
import com.sinabro.shared.util.CustomDialog
import com.sinabro.shared.util.SinabroShareData
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.viewModel

@AndroidEntryPoint
class VocaLearningActivity :
    BaseActivity<ActivityVocaLearningBinding>(R.layout.activity_voca_learning) {
    private val vocaLearningViewModel: VocaLearningViewModel by viewModels()
    private lateinit var vocaLearningAdapter: VocaLearningAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        goAnswer()
        answerCheck()
        clickAnswerCheck()
        checkLoading()
        checkHint()
    }

    //정답 확인
    private fun goAnswer() {
        binding.textVocaLearningOk.setOnClickListener {
            val intent = Intent(this, VocaLearningAnswerActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    //데이터 받아오기
    private fun initView() {
        val data = SinabroShareData
        binding.vocaHint = true
        vocaLearningAdapter = VocaLearningAdapter()
        binding.rcVocaLearning.adapter = vocaLearningAdapter
        showLoading()
        vocaLearningViewModel.getVocaLearningData(data.publisher, data.subject, data.chapter)
        vocaLearningViewModel.vocaLearningData.observe(this) {
            vocaLearningAdapter.setVocaLearning(it.optionList as MutableList<String>)
            binding.vocaGetLearningData = it
        }
    }

    //정답체크
    private fun answerCheck() {
        vocaLearningAdapter.setOnItemClickListener { i ->
            vocaLearningViewModel.answerCheck.value = i == vocaLearningViewModel.vocaLearningData.value?.answer
        }
    }
    //힌트 체크
    private fun checkHint(){
        binding.textVocaLearningDefinitionHint.setOnClickListener {
            binding.vocaHint = false
        }
        binding.textVocaLearningDefinition.setOnClickListener {
            binding.vocaHint = true
        }

    }


    //정답 확인 버튼 클릭
    private fun clickAnswerCheck(){
        binding.textVocaLearningOk.setOnClickListener {
            val answer = vocaLearningViewModel.answerCheck.value
            val definition = vocaLearningViewModel.vocaLearningData.value?.vocaDefinition
            val intent = Intent(this, VocaLearningAnswerActivity::class.java)
            intent.apply {
                putExtra("answer", answer)
                putExtra("definition", definition?.toTypedArray())
            }
            startActivity(intent)
            finish()
        }
    }

    //로딩바
    private fun checkLoading(){
        vocaLearningViewModel.onLoadingEnd.observe(this){
            dismissLoading()
        }
    }


}