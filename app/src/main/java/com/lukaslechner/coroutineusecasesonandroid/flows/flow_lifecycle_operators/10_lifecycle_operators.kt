package com.lukaslechner.coroutineusecasesonandroid.flows.flow_lifecycle_operators

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlin.coroutines.EmptyCoroutineContext

fun main(){
    val flow = flow{
        delay(100)
        println("emitting the first value")
        emit(1)

        delay(100)
        println("emitting the second value")
        emit(2)
    }
    val scope = CoroutineScope(EmptyCoroutineContext)

    // order of the execution lifecycle operator in flows is called flow processing pipeline.
    // In processing pipeline the order of execution is onStart -> onEach -> onCompletion
    // In processing pipeline the code which is executed before the lifecycle operator is called the upstream block
    // and the code which is executed after the lifecycle operator is called the downstream block.
    flow.onCompletion { throwable ->
            println("flow completed $throwable")
        // value of the throwable will be null if the flow completed successfully but
        // if flow not executed successfully then the throwable will holding exception type.
        }
        .onStart {
            println("flow started1")
            emit(0)
            // we can also emit the values from the lifecycle operator and the emitted values will be
            // emitted to the downstream block.
            // we can only emit the type of value emit by the flow from the onStart
            // on which the onStart was applied. If want the to change the type then
            // we can use map operator on the flow.
        }
        .onStart {
            println("flow started2")
            // we can pass multiple onStart method
        }
        .onEach { emittedValue ->
            println("flow's emitted value: $emittedValue")
        }
        .launchIn(scope)



    Thread.sleep(1000)
}