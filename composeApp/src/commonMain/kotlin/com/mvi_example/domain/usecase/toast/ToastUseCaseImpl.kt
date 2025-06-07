package com.mvi_example.domain.usecase.toast

import com.mvi_example.screenView.ScreenEffect

class ToastUseCaseImpl : ToastUseCase {
    override fun prepareToastEffect(message: String): ScreenEffect.ShowToast {
        return ScreenEffect.ShowToast(message = "Usecase says: $message")
    }
}