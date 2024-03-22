package com.lukaslechner.coroutineusecasesonandroid.flows.flow_terminal_operators

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single

suspend fun main(){
    val flow = flow{
        delay(100)
        println("emitting first value")
        emit(1)

        delay(100)
        println("emitting second value")
//        emit(2)
    }
    val item1 = flow.single()
    println("Single item in the flow is $item1")
//    If there are multiple items in the flow then it will thrown IllegalArgumentException.
//    single() is the function to access that single item in the flow
}