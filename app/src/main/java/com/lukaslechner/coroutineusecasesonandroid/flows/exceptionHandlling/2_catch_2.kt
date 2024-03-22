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
                // by using catch operator, we can handle all the exception
                // which occurred in the upstream block of the catch operator.
                // since, we are using catch block above the map, it will not caught the
                // exception of the map.
                emit("Default stock")
            }
            .map{
                throw Exception("Exception occurred inside map")
            }
            .onCompletion { cause: Throwable? ->
                println("flow has completed with: $cause")
//                Conceptually, onCompletion is similar to wrapping the flow collection into a finally block,
//                for example the following imperative snippet:
//                try {
//                    myFlow.collect { value ->
//                        println(value)
//                    }
//                } finally {
//                    println("Done")
//                }
//                since, in this example, the exception is not handled any where.
//                Therefore, it will be propagated into up hierarchy of the coroutine.
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
    // this exception thrown propagated to the terminal operator and thrown there.
    // If any exception occur in the intermediate terminal operator then that latest
    // exception will be thrown to the terminal operator.
}