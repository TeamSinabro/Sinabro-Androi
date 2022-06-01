package com.sinabro.presentation.ui.listenlearning.viewmodel

import androidx.lifecycle.ViewModel
import com.sinabro.domain.usecase.pronounce.GetPronounceSentenceDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListenLearningViewModel @Inject constructor(
    private val getPronounceSentenceDataUseCase : GetPronounceSentenceDataUseCase
) : ViewModel() {

}