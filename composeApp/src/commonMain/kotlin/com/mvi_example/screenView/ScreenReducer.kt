package com.mvi_example.screenView

// The reducer takes the current state and produces, together with the intent, a new state
fun reduce(currentState: ScreenState, intent: ScreenIntent): ScreenState {
    return when (intent) {
        is ScreenIntent.UpdateData -> currentState.copy(title = intent.newData)

        is ScreenIntent.ChangeTitleIntent -> {
            currentState.copy(title = "my new fancy title")
        }

        ScreenIntent.DoSomething -> {
            currentState
        }
    }
}