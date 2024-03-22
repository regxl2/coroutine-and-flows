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
    val item1 = flow.first()
    println("Flow first item value: $item1")
//    After emitting the first value the flow will get cancelled. Therefore, no statement will be printed
//    or value will be emitted after emitting the first value.

    val item2 = flow.first { it > 1 }
    println("Flow first item which value is bigger than 1: $item2")
//    If we want the first value in the flow which is bigger than 1. We will pass
//    the lambda in first terminal operator as shown in the item2 example.
//    But it will execute the first println statement also but not the emit(1) statement.


    val item3 = flowOf<Int>().firstOrNull()
    println("Flow first value is $item3")
//    If there is no element in the flow then first() terminal operator will throw NoSuchElementException.
//    To avoid the exception then we will use firstOrNull() function.
}
