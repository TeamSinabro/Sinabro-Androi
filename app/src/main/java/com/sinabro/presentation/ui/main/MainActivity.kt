package com.sinabro.presentation.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.sinabro.R
import com.sinabro.databinding.ActivityMainBinding
import com.sinabro.presentation.base.BaseActivity
import com.sinabro.presentation.ui.listenlearning.ListenLearningActivity
import com.sinabro.presentation.ui.pronouncelearning.PronounceLearningActivity
import com.sinabro.presentation.ui.qalearning.QALearningActivity
import com.sinabro.presentation.ui.subjectselect.SubjectSelectActivity
import com.sinabro.presentation.ui.vocalearning.VocaLearningActivity
import com.sinabro.presentation.ui.vocasearch.VocaSearchActivity
import com.sinabro.shared.util.SinabroShareData
import timber.log.Timber


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    val sinabroShared = SinabroShareData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        processGoView()
        Timber.d("시나브로 데이터 ${sinabroShared.chapter} + ${sinabroShared.publisher} + ${sinabroShared.subject}")

    }

    //intent 부분
    private fun clickView(activity : Activity){
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }

    //클릭에 따른 분기처리
    private fun processGoView(){
        binding.imgSetting.setOnClickListener {
            clickView(SubjectSelectActivity())
        }
        binding.textPronounceLearning.setOnClickListener {
            clickView(PronounceLearningActivity())
        }
        binding.textVocaLearning.setOnClickListener {
            clickView(VocaLearningActivity())
        }
        binding.textVocaSearch.setOnClickListener {
            clickView(VocaSearchActivity())
        }
        binding.textListenLearning.setOnClickListener {
            clickView(ListenLearningActivity())
        }
        binding.textQaLearning.setOnClickListener {
            clickView(QALearningActivity())
        }
    }







}