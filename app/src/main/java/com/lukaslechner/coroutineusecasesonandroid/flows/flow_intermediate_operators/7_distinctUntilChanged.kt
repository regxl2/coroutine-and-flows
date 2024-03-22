package com.lukaslechner.coroutineusecasesonandroid.flows.flow_intermediate_operators

import kotlinx.coroutines.flow.*

suspend fun main(){
    flowOf(1, 1, 1, 2, 2, 3, 4, 4, 5, 1)
        .distinctUntilChanged()
        .collect{ emittedValue ->
            println("Flow: $emittedValue")
        }
    // it will ignore the consecutive similar values
}