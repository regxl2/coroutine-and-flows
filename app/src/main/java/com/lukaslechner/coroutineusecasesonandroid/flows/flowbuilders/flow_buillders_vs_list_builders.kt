package com.lukaslechner.coroutineusecasesonandroid.flows.flowbuilders

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

fun main(){
    val flow = flow{
        delay(100)
        println("emitting first value")
        emit(1)

        delay(100)
        println("emitting second value")
        emit(2)
    }
    // The code inside flow builder will not be executed unless until
    // terminal operators are not called on the flow builder.

    val list = buildList{
        println("adding first value")
        add(1)
        println("adding second value")
        add(2)
    }
    // Whereas, the code inside the list builder will be executed
}