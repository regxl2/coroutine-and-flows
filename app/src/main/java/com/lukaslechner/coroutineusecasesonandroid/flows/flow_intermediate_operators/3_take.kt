package com.lukaslechner.coroutineusecasesonandroid.flows.flow_intermediate_operators

import kotlinx.coroutines.flow.*

suspend fun main(){
    flowOf(1, 2, 3, 4, 5)
        .take(3)
        .collect{emittedValue ->
            println("flow: $emittedValue")
        }
    // by using take, it will emit first 3 values

    println()

    flowOf(5, 3, 4, 2, 1 )
        .takeWhile { num -> num!=4}
        .collect{emittedValue ->
            println("flow: $emittedValue")
        }
    // by using takeWhile, as the condition breaks it stop emitting the values
}