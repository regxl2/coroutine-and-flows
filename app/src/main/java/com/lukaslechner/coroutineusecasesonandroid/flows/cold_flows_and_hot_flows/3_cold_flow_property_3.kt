package com.lukaslechner.coroutineusecasesonandroid.flows.cold_flows_and_hot_flows

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

//Property3: - coldFlows emit individual emissions to every collector.
suspend fun main():Unit = coroutineScope{
    launch{
        coldFlow()
            .onCompletion {
                println("collector1 is completed")
            }
            .collect{ emittedValue ->
                println("collector1 collected value: $emittedValue")
            }
    }
    launch{
        coldFlow()
            .onCompletion {
                println("collector2 is completed")
            }
            .collect{ emittedValue ->
                println("collector2 collected value: $emittedValue")
            }
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
    // you can see these println("emitting n") statement are printed for each collector individually.
    // It means the coldFlows emit individual emissions to every collector.
}
