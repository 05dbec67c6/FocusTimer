package com.mvi_example.screenView

import com.mvi_example.domain.model.DataInterface
import com.mvi_example.domain.usecase.toast.ToastUseCase

// The execution result is what the Executor will send to the store, which processes it, it is either
// an intent or an effect or nothing, or both, but never everything at once -1
sealed class ExecutionResult {
    data class ProcessIntent(val intent: ScreenIntent) : ExecutionResult()
    data class ProcessEffect(val effect: ScreenEffect) : ExecutionResult()

    // For overachiever that need some things more convenient
    data class ProcessIntentAndEmitEffect(val intent: ScreenIntent, val effect: ScreenEffect) :
        ExecutionResult()

    // If an action results in nothing
    data object NoOp : ExecutionResult()

}

// The executor class take the action and will send a result to the store, if it is more complex to
// decide on the type, or needs something to create the intent it should use injected dependencies
// for example the UseCase
class ScreenExecutor(
    private val toastUseCase: ToastUseCase,
    private val dataInterface: DataInterface,
) {
    fun processViewAction(action: ViewAction): ExecutionResult {
        return when (action) {
            is ViewAction.ButtonClickThatChangesState -> {
                ExecutionResult.ProcessIntent(ScreenIntent.ChangeTitleIntent("NewString"))
            }

            is ViewAction.ShowToastAndChangeTitle -> ExecutionResult.ProcessIntentAndEmitEffect(
                intent = ScreenIntent.ChangeTitleIntent("NewStringToo"),
                effect = ScreenEffect.ShowToast("Toasti McToastFace")
            )

            is ViewAction.JustShowToast -> ExecutionResult.ProcessEffect(
                toastUseCase.prepareToastEffect(dataInterface.data.value)
            )
        }
    }
}