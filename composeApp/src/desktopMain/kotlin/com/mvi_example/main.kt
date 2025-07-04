package com.mvi_example

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.mvi_example.di.appModule
import org.koin.core.context.startKoin

fun main() = application {
    startKoin{
        modules(appModule)
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "MVI-Example",
    ) {
        App()
    }
}