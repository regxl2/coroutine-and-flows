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
                throw Exception("Exception occurred in mapping the values.")
                // exception is propagated to the  onCompletion operator, since it is last executing operator and
                // rethrown there and all the between code is skipped.
                // since, the onCompletion operator is in the try/catch block.
                // Therefore, the exception is handled by try/catch block.
            }

        // As we know, the flow is inside the coroutine and flow is throwing exception
        // So, in coroutine the exception can be handled either surrounding the exception causing
        // block with try/catch otherwise the exception will be propagated up in the hierarchy or
        // we can use exception handler in the top level coroutine of the scope or in the scope.
        try {
            stockFlow
                .onCompletion {throwable ->
                    if(throwable==null){
                        println("Flow has completed successfully")
                    }
                    else{
                        println("Flow has completed exceptionally: $throwable")
                    }
                    // if flow has completed successfully then the throwable value will be null.
                    // but if it is not null then flow was completed with exception.
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
}