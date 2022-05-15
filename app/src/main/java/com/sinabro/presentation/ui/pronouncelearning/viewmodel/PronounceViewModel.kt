package com.sinabro.presentation.ui.pronouncelearning.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinabro.domain.model.request.PronouncePostItem
import com.sinabro.domain.model.response.PronounceGetSentenceData
import com.sinabro.domain.model.response.PronouncePostData
import com.sinabro.domain.usecase.GetPronounceSentenceDataUseCase
import com.sinabro.domain.usecase.PostPronounceDataUseCase
import com.sinabro.shared.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PronounceViewModel @Inject constructor(
    private val postPronounceDataUseClass: PostPronounceDataUseCase,
    private val getPronounceSentenceDataUseCase: GetPronounceSentenceDataUseCase
)  : ViewModel() {

    var audionContents : MutableLiveData<String> = MutableLiveData()



    //발음 데이터
    private var _pronounceData : MutableLiveData<PronouncePostData> = MutableLiveData()
    val pronounceData : LiveData<PronouncePostData>
        get() = _pronounceData

    //녹음중
    var recording : MutableLiveData<Boolean> = MutableLiveData()


    //발음 평가 문제
    private var _pronounceSentence : MutableLiveData<PronounceGetSentenceData> = MutableLiveData()
    val pronounceSentence : LiveData<PronounceGetSentenceData>
        get() = _pronounceSentence

    //발음 평가 서버 통신
     fun postPronounce(pronouncePostItem: PronouncePostItem){
        viewModelScope.launch {
            runCatching { postPronounceDataUseClass(pronouncePostItem) }
                .onSuccess {
                    _pronounceData.value = it
                    Timber.d("pronounce 데이터 통신 성공!")
                }
                .onFailure {
                    it.printStackTrace()
                    Timber.d("pronounce 데이터 통신 실패!")
                }
        }
    }

    //발음 평가 문제 서버 통신
    fun getPronounceSentence(publisher : String, subject : String, chapter : Int){
        viewModelScope.launch {
            runCatching { getPronounceSentenceDataUseCase(publisher, subject, chapter) }
                .onSuccess {
                    _pronounceSentence.value = it
                    Timber.d("pronounce 문제 통신 성공!")
                }
                .onFailure {
                    Timber.d("pronounce 문제 통신 실패!")
                }
        }


    }
}