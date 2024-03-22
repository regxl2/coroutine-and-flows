package com.lukaslechner.coroutineusecasesonandroid.flows.flow_terminal_operators

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

// One of the most important difference between the launchIn and collect is that collect is suspend function
// whereas launchIn is the regular function.

fun main(){
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
        println("flow1 emitted value: $emittedValue")
    }.launchIn(scope)

    flow.onEach {emittedValue ->
        println("flow2 emitted value: $emittedValue")
    }.launchIn(scope)

//    launchIn is the equivalent to scope.launch{} and onEach is the equivalent to flow.collect
//    The above syntax is the shortcut of the below commented code
//    scope.launch {
//        flow.collect{ emittedValue ->
//            println("flow emitted value: $emittedValue")
//        }
//    }
//    since, the internal implement of the launchIn is the above code then, calling two will launchIn
//    will run parallel


    Thread.sleep(1000)


    scope.launch {
        flow.collect{emittedValue ->
            println("collect1 emitted value: $emittedValue")
        }
        flow.collect{emittedValue ->
            println("collect2 emitted value: $emittedValue")
        }
    }
    // collect code will run sequentially because the collect() function is a
    // blocking function that means until the code inside the collect function will
    // not be completed then control to other collect() function will be passed.

    Thread.sleep(1000)
}