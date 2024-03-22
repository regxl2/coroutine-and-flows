package com.lukaslechner.coroutineusecasesonandroid.flows.exceptionHandlling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

suspend fun main() = coroutineScope {
    println("Thread: ${Thread.currentThread().name} started")

    launch {
        println("Thread: ${Thread.currentThread().name}")

        val stockFlow = stockFlow()
            .map{
                println("Inside map")
                "$it*"
            }

        try {
            stockFlow
                .catch {throwable ->
                    println("Exception is caught: $throwable")
                    // by using catch operator, we can handle all the exceptions
                    // which occurred in the upstream block of the catch operator.
                    // Notice that control will only reach the block if any exception occurred
                }
                .onCompletion { throwable ->
                    println("flow has completed: $throwable")
                }
                .collect{stock ->
                    println("Collected: $stock")
                }
        }
        catch (e: Exception){
            println("Handle exception in catch block $e")
        }
    }

    println("Thread: ${Thread.currentThread().name} ended")
}

private fun stockFlow() : Flow<String> = flow {
    emit("Apple")
    emit("Microsoft")
    throw Exception("Network request failed")
    // this exception thrown and propagated to the last executing operator and thrown there.
    // In this case, it will be caught by catch operator. since, catch operator can caught upstream block exception.
}
