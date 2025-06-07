package com.mvi_example.screenView

import com.mvi_example.domain.model.DataInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// The heart of the architecture. It functions like a video game console orchestrating everything.
// It exposes the state to the View. It delegates the ViewActions to the Executor and it processes
// the ExecutorResults (eg Intent or Effect), and it does it all for free.
class ScreenStore(
    private val storeScope: CoroutineScope,
    private val dataSource: DataInterface,
    private val executor: ScreenExecutor,
) {

    // Exposing the ui state to the view
    private val _screenState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState())
    val screenState: StateFlow<ScreenState> = _screenState

    // If we have effects (one time events), it will come in here with a shared flow
    private val _effects = MutableSharedFlow<ScreenEffect>()
    val effects: SharedFlow<ScreenEffect> = _effects

    init {
        // Collecting the data to feed the screen, its a state change, so we call the processIntent
        storeScope.launch {
            dataSource.data.collect { newData ->
                processIntent(ScreenIntent.UpdateData(newData))
            }
        }
    }

    // a ViewAction is something the user does, when he touches the screen with his/her chubby fingers
    fun onViewAction(action: ViewAction) {
        storeScope.launch {
            when (val result: ExecutionResult = executor.processViewAction(action)) {
                is ExecutionResult.ProcessIntentAndEmitEffect -> {
                    processIntent(result.intent)
                    _effects.emit(result.effect)

                }

                is ExecutionResult.ProcessIntent -> processIntent(result.intent)
                is ExecutionResult.ProcessEffect -> _effects.emit(result.effect)
                ExecutionResult.NoOp -> {}
            }
        }
    }

    private fun processIntent(intent: ScreenIntent) {
        storeScope.launch {
            _screenState.update { currentState ->
                reduce(currentState, intent)
            }
        }
    }
}
