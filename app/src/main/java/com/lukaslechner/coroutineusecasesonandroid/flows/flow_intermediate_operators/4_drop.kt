package com.lukaslechner.coroutineusecasesonandroid.flows.flow_intermediate_operators

import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.flowOf

suspend fun main(){
    flowOf(1, 2, 3, 4, 5)
        .drop(3)
        .collect{emittedValue ->
            println("flow: $emittedValue")
        }
    // by using drop, it will drop first 3 values

    println()

    flowOf(5, 3, 4, 2, 1 )
        .dropWhile { num -> num!=4}
        .collect{emittedValue ->
            println("flow: $emittedValue")
        }
    // by using dropWhile, as the condition breaks it stop dropping the values and start emitting values.
}