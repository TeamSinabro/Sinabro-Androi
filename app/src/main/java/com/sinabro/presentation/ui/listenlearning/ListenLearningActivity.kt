package com.sinabro.presentation.ui.listenlearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.sinabro.R
import com.sinabro.databinding.ActivityListenLearningBinding
import com.sinabro.presentation.base.BaseActivity
import com.sinabro.presentation.ui.listenlearning.viewmodel.ListenLearningViewModel
import com.sinabro.shared.util.SinabroShareData
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.viewModel

@AndroidEntryPoint
class ListenLearningActivity : BaseActivity<ActivityListenLearningBinding>(R.layout.activity_listen_learning) {
    private val listenLearningViewModel : ListenLearningViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSentenceData()
        checkLoading()
    }


    //문장 데이터 받아오기
    private fun initSentenceData(){
        val data = SinabroShareData
        listenLearningViewModel.getSentenceData(data.publisher, data.subject, data.chapter)
    }


    //로딩바
    private fun checkLoading(){
        listenLearningViewModel.onLoadingEnd.observe(this){
            dismissLoading()
        }
    }
}