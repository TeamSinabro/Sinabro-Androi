package com.sinabro.presentation.ui.listenlearning.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinabro.domain.usecase.pronounce.GetPronounceSentenceDataUseCase
import com.sinabro.presentation.base.LoadedViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ListenLearningViewModel @Inject constructor(
    private val getPronounceSentenceDataUseCase : GetPronounceSentenceDataUseCase
) : ViewModel(), LoadedViewModel {

    //듣기 학습 문장 데이터
    var sentence = MutableLiveData<String>()



    //문장 데이터 받아오기
    fun getSentenceData(publisher : String, subject : String, chapter : Int ){

        viewModelScope.launch {
            runCatching { getPronounceSentenceDataUseCase(publisher, subject, chapter) }
                .onSuccess {
                    sentence.value = it.problem
                    Timber.d("듣기 학습 문장 받아오기 완료")
                }
                .onFailure {
                    Timber.d("듣기 학습 문장 받아오기 실패")
                }.also {
                    onLoadingEnd.value = true
                }


        }


    }

    override val onLoadingEnd =MutableLiveData<Boolean>()

}