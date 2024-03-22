package com.lukaslechner.coroutineusecasesonandroid.flows.flow_terminal_operators

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

suspend fun main(){
    val flow = flow{
        delay(100)
        println("emitting first value")
        emit(1)

        delay(100)
        println("emitting second value")
        emit(2)
    }
    val item1 = flow.last()
    println("last item in the flow is $item1")

}