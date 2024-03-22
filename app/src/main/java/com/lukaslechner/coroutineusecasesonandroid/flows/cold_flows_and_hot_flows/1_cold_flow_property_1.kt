package com.lukaslechner.coroutineusecasesonandroid.flows.cold_flows_and_hot_flows

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

//Property1: - coldFlows only become active on collection
suspend fun main() = coroutineScope{
    coldFlow()
        .collect{ emittedValue ->
            println("Collected value: $emittedValue")
        }
}

private fun coldFlow() = flow{
    delay(1000)
    println("emitting 1")
    emit(1)
    delay(1000)
    println("emitting 2")
    emit(2)
    delay(1000)
    println("emitting 3")
    emit(3)
}
