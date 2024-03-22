package com.lukaslechner.coroutineusecasesonandroid.flows.flow_intermediate_operators

import kotlinx.coroutines.flow.*

suspend fun main(){
    flowOf(1, 2, 3, 4, 5)
        .withIndex()
        .collect{ emittedValue ->
            println(emittedValue)
        }
    // it emitted IndexedValue Object with index and value
}