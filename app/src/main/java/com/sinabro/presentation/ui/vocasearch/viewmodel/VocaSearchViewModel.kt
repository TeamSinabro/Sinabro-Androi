package com.sinabro.presentation.ui.vocasearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinabro.domain.model.response.vocasearch.VocaSearchData
import com.sinabro.domain.usecase.vocasearch.GetVocaSearchDataUseCase
import com.sinabro.shared.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VocaSearchViewModel @Inject constructor(
    private val getVocaSearchDataUseCase: GetVocaSearchDataUseCase
) : ViewModel() {

    //검색 결과 데이터
    private var _vocaSearchData : MutableLiveData<VocaSearchData> = MutableLiveData()
    val vocaSearchData : LiveData<VocaSearchData>
        get() = _vocaSearchData

    //화살표 클릭
    private var _vocaSearchTextEvent : MutableLiveData<Boolean> = MutableLiveData(true)
    val vocaSearchTextEvent : LiveData<Boolean>
        get() = _vocaSearchTextEvent

    fun setVocaSearchTextEvent(event : Boolean){
        _vocaSearchTextEvent.value = event
    }

    //이벤트 감지
    var event : SingleLiveEvent<Boolean> = SingleLiveEvent()

    //검색 데이터 받아오기
    fun getVocaSearchData(keyword : String){
        viewModelScope.launch {
            runCatching { getVocaSearchDataUseCase(keyword) }
                .onSuccess {
                    _vocaSearchData.value = it
                    Timber.d("검색 데이터 받아오기 완료 $it")
                }
                .onFailure {
                    Timber.d("검색 데이터 받아오기 실패")
                }
        }


    }
}