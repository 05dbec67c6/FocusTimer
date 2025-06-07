package com.mvi_example

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.mvi_example.screenView.MainScreen
import com.mvi_example.screenView.ScreenEffect
import com.mvi_example.screenView.ScreenStore
import com.mvi_example.screenView.ViewAction
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

expect fun showToast(message: String, duration: ToastDuration)

enum class ToastDuration {
    SHORT, LONG
}

@Composable
fun App() {
    MaterialTheme {

        // In multi screen app, will have navController here and inject it in the top level
        // of the specific screen
        val composableScope = rememberCoroutineScope()

        val screenStore: ScreenStore = koinInject {
            parametersOf(composableScope)
        }

        val state by screenStore.screenState.collectAsState()

        LaunchedEffect(Unit) { // Keyed on Unit to run once and persist
            screenStore.effects.collectLatest { effect -> // Use collectLatest or collect
                when (effect) {
                    is ScreenEffect.ShowToast -> {
                        showToast(effect.message, ToastDuration.SHORT) // Call your expect function
                    }
                    // Handle other effects if any
                    ScreenEffect.ButtonClickThatChangesState -> {}
                }
            }
        }
        MainScreen(
            state = state,
            onChangeString = {
                screenStore.onViewAction(ViewAction.ButtonClickThatChangesState)
            },
            onTriggerToast = {
                screenStore.onViewAction(ViewAction.JustShowToast)
            },
        )
    }
}
