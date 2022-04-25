package com.sinabro.presentation.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.sinabro.R
import com.sinabro.databinding.ActivityMainBinding
import com.sinabro.presentation.base.BaseActivity
import com.sinabro.presentation.ui.pronouncelearning.PronounceLearningActivity
import com.sinabro.presentation.ui.subjectselect.SubjectSelectActivity
import com.sinabro.presentation.ui.vocalearning.VocaLearningActivity
import com.sinabro.presentation.ui.vocasearch.VocaSearchActivity


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        processGoView()

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
    }







}