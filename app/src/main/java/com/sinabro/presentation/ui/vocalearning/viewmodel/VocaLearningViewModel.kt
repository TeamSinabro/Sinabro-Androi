package com.sinabro.presentation.ui.vocalearning.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinabro.domain.model.response.vocalearning.VocaGetLearningData
import com.sinabro.domain.usecase.vocalearning.GetVocaLearningDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VocaLearningViewModel @Inject constructor(
    private val getVocaLearningDataUseCase: GetVocaLearningDataUseCase
) : ViewModel() {
    
    
    private var _vocaLearningData : MutableLiveData<VocaGetLearningData> = MutableLiveData()
    val vocaLearningData : LiveData<VocaGetLearningData>
        get() = _vocaLearningData


    suspend fun getVocaLearningData(publisher : String, subject : String, chapter : Int){
        viewModelScope.launch {
            runCatching { getVocaLearningDataUseCase(publisher, subject, chapter) }
                .onSuccess {
                    _vocaLearningData.value = it
                    Timber.d("어휘 학습 데이터 서버 통신 완료")
                }
                .onFailure {
                    it.printStackTrace()
                    Timber.d("어휘 학습 데이터 서버 통신 실패")
                }
        }


    }
}