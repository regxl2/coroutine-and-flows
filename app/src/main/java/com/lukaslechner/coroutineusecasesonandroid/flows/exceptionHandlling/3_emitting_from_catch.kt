package com.lukaslechner.coroutineusecasesonandroid.flows.exceptionHandlling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException

suspend fun main() = coroutineScope {
    println("Thread: ${Thread.currentThread().name} started")

    launch {
        println("Thread: ${Thread.currentThread().name}")

        val stockFlow = stockFlow()
            .catch {throwable ->
                println("Exception is caught: $throwable")
                emit("Default stock")
            }
            .onCompletion { cause: Throwable? ->
                println("flow has completed with: $cause")
            }
            .collect{stock ->
                println("Collected: $stock")
            }
    }

    println("Thread: ${Thread.currentThread().name} ended")
}

private fun stockFlow() : Flow<String> = flow {
    emit("Apple")
    emit("Microsoft")
    throw IOException("Network request failed")
}