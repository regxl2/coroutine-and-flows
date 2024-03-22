package com.lukaslechner.coroutineusecasesonandroid.flows.cold_flows_and_hot_flows

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

//Property2: - coldFlows becomes inactive on cancellation of the collecting coroutine
suspend fun main() = coroutineScope{
    val job = launch{
        coldFlow()
            .onCompletion { throwable->
                println("Flow is completed $throwable")
            }
            .collect{ emittedValue ->
                println("Collected value: $emittedValue")
            }
    }
    delay(1500)
    job.cancelAndJoin()
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
