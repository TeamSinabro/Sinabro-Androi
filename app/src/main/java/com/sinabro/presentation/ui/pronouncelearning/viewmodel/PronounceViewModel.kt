package com.sinabro.presentation.ui.pronouncelearning.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinabro.domain.model.request.PronouncePostItem
import com.sinabro.domain.model.response.PronouncePostData
import com.sinabro.domain.usecase.PostPronounceDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PronounceViewModel @Inject constructor(
    private val postPronounceDataUseClass: PostPronounceDataUseCase
)  : ViewModel() {

    var audionContents : MutableLiveData<String> = MutableLiveData()



    //발음 데이터
    private var _pronounceData : MutableLiveData<PronouncePostData> = MutableLiveData()
    val pronounceData : LiveData<PronouncePostData>
        get() = _pronounceData



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
}