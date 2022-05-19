package com.sinabro.presentation.ui.vocasearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.sinabro.R
import com.sinabro.databinding.ActivityVocaSearchBinding
import com.sinabro.presentation.base.BaseActivity
import com.sinabro.presentation.ui.vocasearch.viewmodel.VocaSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.viewModel
import javax.inject.Inject

@AndroidEntryPoint
class VocaSearchActivity : BaseActivity<ActivityVocaSearchBinding>(R.layout.activity_voca_search) {

    private val vocaSearchviewModel : VocaSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goMain()
    }

    //메인 이동
    private fun goMain(){
        binding.textVocaSearchOk.setOnClickListener {
            finish()
        }
    }



}