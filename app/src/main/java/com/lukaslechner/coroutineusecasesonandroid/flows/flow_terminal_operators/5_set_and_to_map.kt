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

    // toSet() method: -
    // first it will execute all the code inside the flow builder then it will print the print set statement.
    val set = flow.toSet()
    println("Set:- $set")

    //toList() method: -
    // similar to the set, it will execute all the code inside the flow builder then it will print the print list statement.
    val list = flow.toList()
    println("List:- $list")
}