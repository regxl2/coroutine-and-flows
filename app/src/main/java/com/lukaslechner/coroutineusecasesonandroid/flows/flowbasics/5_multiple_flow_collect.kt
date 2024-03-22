package com.lukaslechner.coroutineusecasesonandroid.flows.flowbasics

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

suspend fun main():Unit = coroutineScope {
    val myFlow = intFlow()

//    sequential collection since collect is suspending function.
//    myFlow.collect{
//        println("collected1: $it")
//    }
//    myFlow.collect{
//        println("collected1: $it")
//    }

//    parallel collection
    launch {
        myFlow.collect{
            println("collected1: $it")
        }
    }
    launch {
        myFlow.collect{
            println("collected1: $it")
        }
    }
}

fun intFlow() = flow {
    emit(1)
    emit(2)
    emit(3)
}