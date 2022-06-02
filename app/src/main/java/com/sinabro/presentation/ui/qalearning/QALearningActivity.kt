package com.sinabro.presentation.ui.qalearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sinabro.R
import com.sinabro.databinding.ActivityQalearningBinding
import com.sinabro.presentation.base.BaseActivity
import com.sinabro.presentation.ui.qalearning.viewmodel.QALearningViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.viewModel

@AndroidEntryPoint
class QALearningActivity : BaseActivity<ActivityQalearningBinding>(R.layout.activity_qalearning) {
    private val qaLearningViewModel : QALearningViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setData()
        initQAData()
        checkLoading()
    }

    //데이터 받아오기
    private fun setData(){
        binding.textQaLearningSearch.setOnClickListener {
            val question = binding.etQaSentence.text.toString()
            qaLearningViewModel.getQALearningData(question)
        }
    }

    //데이터 넣기
    private fun initQAData(){
        qaLearningViewModel.answer.observe(this){
            binding.qaData = it
        }
    }

    //로딩 체크
    private fun checkLoading(){
        qaLearningViewModel.onLoadingEnd.observe(this){
            dismissLoading()
        }
    }

}