package com.lukaslechner.coroutineusecasesonandroid.flows.exceptionHandlling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*


suspend fun main() = coroutineScope{
    flow{
        emit(1)
    }.catch{
        println("Handled exception in catch operator.")
    }.collect {
        throw Exception("Exception in collect")
    }
    // after exception thrown downstream and we are not emitting any additional values.
}