package com.mvi_example.di

import com.mvi_example.data.DataInterfaceImpl
import com.mvi_example.domain.model.DataInterface
import com.mvi_example.domain.usecase.toast.ToastUseCase
import com.mvi_example.domain.usecase.toast.ToastUseCaseImpl
import com.mvi_example.screenView.ScreenExecutor
import com.mvi_example.screenView.ScreenStore
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val appModule = module {

    single<DataInterface> { DataInterfaceImpl() }

    single<ToastUseCase> { ToastUseCaseImpl() }

    single<ScreenExecutor> { ScreenExecutor(get(), get()) }

    factory { (scope: CoroutineScope) ->
        ScreenStore(
            storeScope = scope,
            dataSource = get(),
            executor = get(),
        )
    }
}