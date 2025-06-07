package com.mvi_example.screenView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

// The screen view in form of a composable
@Composable
fun MainScreen(
    state: ScreenState,
    onChangeString: () -> Unit,
    onTriggerToast: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Text(state.title)
            Button(onClick = onChangeString) {
                Text("change the string")
            }
            Button(onClick = { onTriggerToast() }) {
                Text("trigger toast")
            }
        }
    }
}

