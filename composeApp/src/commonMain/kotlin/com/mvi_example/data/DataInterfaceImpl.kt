package com.mvi_example.data

import com.mvi_example.domain.model.DataInterface
import kotlinx.coroutines.flow.MutableStateFlow

class DataInterfaceImpl : DataInterface {
    override val data = MutableStateFlow("DefaultStringFromDataSource")
}