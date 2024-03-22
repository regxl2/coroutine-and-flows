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

    // value passed inside the fold is the initial value of the accumulator.
    // inside the lambda function we just the emitted values to the accumulator value.
    // This fold terminal is useful in summing up all values of items inside the flow.
    val item = flow.fold(5){ accumulator, emittedValue ->
        accumulator + emittedValue
    }
    println("Value of the item is $item")
}