package com.mvi_example.domain.usecase.toast

import com.mvi_example.screenView.ScreenEffect

interface ToastUseCase {
    fun prepareToastEffect(message: String): ScreenEffect.ShowToast
}



