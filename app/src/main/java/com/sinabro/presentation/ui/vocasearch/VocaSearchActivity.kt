package com.sinabro.presentation.ui.vocasearch

import android.os.Bundle
import androidx.activity.viewModels
import com.sinabro.R
import com.sinabro.databinding.ActivityVocaSearchBinding
import com.sinabro.presentation.base.BaseActivity
import com.sinabro.presentation.ui.vocasearch.viewmodel.VocaSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VocaSearchActivity : BaseActivity<ActivityVocaSearchBinding>(R.layout.activity_voca_search) {
    private lateinit var vocaSearchAdapter : VocaSearchAdapter
    private val vocaSearchviewModel: VocaSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        clickSearchBtn()
        goMain()
        clickVocaDefinition()
        checkEvent()
    }

    //메인 이동
    private fun goMain() {
        binding.textVocaSearchOk.setOnClickListener {
            finish()
        }
    }

    private fun initView(){
        binding.vocaSearchViewModel = vocaSearchviewModel
    }
    //검색 버튼 클릭
    private fun clickSearchBtn() {
        binding.textVocaSearch.setOnClickListener {
            val text = binding.etVocaSearch.text.toString()
            with(vocaSearchviewModel){
                getVocaSearchData(text)
                setVocaSearchTextEvent(false)
            }
            vocaSearchviewModel.vocaSearchData.observe(this){
                binding.vocaSearchData = it
                vocaSearchviewModel.event.value = true
                vocaSearchAdapter = VocaSearchAdapter()
                binding.rcVocaSearchSentence.adapter = vocaSearchAdapter
                vocaSearchAdapter.setVocaSearchData(
                it.sentence as MutableList<String>, it.keywordSource as MutableList<String>
                )
            }
        }
    }

    //단어 정의 클릭
    private fun clickVocaDefinition() {
        binding.textVocaDefinitionTitle.setOnClickListener {
            val textEvent = vocaSearchviewModel.vocaSearchTextEvent.value ?: true
            vocaSearchviewModel.setVocaSearchTextEvent(!textEvent)
            vocaSearchviewModel.event.value = true
        }
    }

    //클릭시 이벤트
    private fun checkEvent(){
        vocaSearchviewModel.event.observe(this){
            binding.vocaSearchViewModel = vocaSearchviewModel
        }

    }
}