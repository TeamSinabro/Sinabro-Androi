package com.sinabro.presentation.base

import androidx.lifecycle.MutableLiveData

interface LoadedViewModel {
    val onLoadingEnd: MutableLiveData<Boolean>
}