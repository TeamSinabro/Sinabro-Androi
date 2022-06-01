package com.sinabro.presentation.ui.pronouncelearning.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinabro.domain.model.request.PronouncePostItem
import com.sinabro.domain.model.response.pronounce.PronounceGetSentenceData
import com.sinabro.domain.model.response.pronounce.PronouncePostData
import com.sinabro.domain.usecase.pronounce.GetPronounceSentenceDataUseCase
import com.sinabro.domain.usecase.pronounce.PostPronounceDataUseCase
import com.sinabro.presentation.base.LoadedViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PronounceViewModel @Inject constructor(
    private val postPronounceDataUseClass: PostPronounceDataUseCase,
    private val getPronounceSentenceDataUseCase: GetPronounceSentenceDataUseCase
)  : ViewModel(), LoadedViewModel {
    override val onLoadingEnd = MutableLiveData<Boolean>()

    //음성인식 데이터
    var sttData = MutableLiveData<String>()

    //음성 녹음 job 멈췄을때
    var state = MutableLiveData<Boolean>()


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
                    Timber.d("pronounce 데이터 ${it.recognized}")
                    Timber.d("pronounce 데이터 통신 성공!")
                }
                .onFailure {
                    it.printStackTrace()
                    Timber.d("pronounce 데이터 통신 실패!")
                }
                .also {
                    onLoadingEnd.value = true
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
                    it.printStackTrace()
                    Timber.d("pronounce 문제 통신 실패!")
                }
                .also {
                    onLoadingEnd.value = true
                }
        }


    }
}