package com.lukaslechner.coroutineusecasesonandroid.flows.flow_intermediate_operators

import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.transform

suspend fun main(){
    flowOf(1, 2, 3, 4, 5)
        .transform{
            emit(it)
            emit(it*10)
            // difference between map and transform is that we cannot emit multiple values
            // from the map whereas we can emit multiple values from transform.
        }
        .collect{emittedValue ->
            println("flow: $emittedValue")
        }
}