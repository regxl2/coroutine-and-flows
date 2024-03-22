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

    // this reduce terminal operator is similar to the fold operator but the difference is
    // in the fold operator we can give initial value to the accumulator but in reduce operator the
    // initial value of the accumulator is zero.
    val item = flow.reduce { accumulator, emittedValue ->
        accumulator + emittedValue
    }
    println("The value of the item is $item")
}