package com.sinabro.presentation.ui.vocalearning.viewmodel

import androidx.lifecycle.ViewModel
import com.sinabro.domain.usecase.vocalearning.GetVocaLearningDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VocaLearningViewModel @Inject constructor(
    private val getVocaLearningDataUseCase: GetVocaLearningDataUseCase
) : ViewModel() {

}