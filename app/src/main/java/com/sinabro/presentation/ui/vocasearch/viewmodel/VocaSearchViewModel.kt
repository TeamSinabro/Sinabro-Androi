package com.sinabro.presentation.ui.vocasearch.viewmodel

import androidx.lifecycle.ViewModel
import com.sinabro.domain.usecase.vocasearch.GetVocaSearchDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VocaSearchViewModel @Inject constructor(
    private val getVocaSearchDataUseCase: GetVocaSearchDataUseCase
) : ViewModel() {


}