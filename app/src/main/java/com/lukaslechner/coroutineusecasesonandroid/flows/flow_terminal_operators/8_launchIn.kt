package com.lukaslechner.coroutineusecasesonandroid.flows.flow_terminal_operators

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.coroutines.EmptyCoroutineContext

suspend fun main(){
    val flow = flow {
        delay(100)
        println("emitting the first value")
        emit(1)
        delay(100)
        println("emitting the second value")
        emit(2)
    }

    val scope = CoroutineScope(EmptyCoroutineContext)
    flow.onEach {emittedValue ->
        println("flow emitted value: $emittedValue")
    }.launchIn(scope)

//    launchIn is the equivalent to scope.launch{} and onEach is the equivalent to flow.collect
//    The above syntax is the shortcut of the below commented code
//    scope.launch {
//        flow.collect{ emittedValue ->
//            println("flow emitted value: $emittedValue")
//        }
//    }

    Thread.sleep(1000)
}