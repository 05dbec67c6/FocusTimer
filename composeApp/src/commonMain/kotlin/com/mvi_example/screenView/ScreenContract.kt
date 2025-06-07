package com.mvi_example.screenView

// The screen contract typically defines everything the screen needs to render, react or communicate
// e.g. State, Intents, Effects, ViewActions etc.

// Intent is used for state changes, not for one time thingys like toasts, they go into effects
sealed class ScreenIntent {
    data class UpdateData(val newData: String) : ScreenIntent()
    data class ChangeTitleIntent(val newString: String) : ScreenIntent()
    data object DoSomething : ScreenIntent()
}

// Effects also called labels are one time events that do not change the state
sealed class ScreenEffect {
    data class ShowToast(val message: String) : ScreenEffect()
    data object ButtonClickThatChangesState : ScreenEffect()
}

// ViewAction is an event that goes from the view to the store, which will then send it to the
// executor to execute it
sealed class ViewAction {
    data object ButtonClickThatChangesState : ViewAction()
    data object ShowToastAndChangeTitle : ViewAction()
    data object JustShowToast : ViewAction()
}

// the state of the screen which means a definition what the UI should display
data class ScreenState(
    val title: String = "DefaultUiStateString",
)