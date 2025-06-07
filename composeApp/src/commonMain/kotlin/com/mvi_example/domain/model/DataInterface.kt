package com.mvi_example.domain.model

import kotlinx.coroutines.flow.StateFlow

interface DataInterface {
    val data: StateFlow<String>
}