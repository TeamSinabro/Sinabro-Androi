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

    }



}