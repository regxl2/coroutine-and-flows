package com.lukaslechner.coroutineusecasesonandroid.playground.structuredconcurrency

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val scope = CoroutineScope(Dispatchers.Default)
    scope.launch {
        launch{
            delay(1000)
            println("Coroutine1 was completed")
        }.invokeOnCompletion {throwable ->
            if(throwable is CancellationException){
                println("Coroutine1 was cancelled")
            }
        }
        launch{
            delay(1000)
            println("Coroutine2 was completed")
        }.invokeOnCompletion {throwable ->
            if(throwable is CancellationException){
                println("Coroutine2 was cancelled")
            }
        }
    }
    scope.coroutineContext[Job]!!.cancelAndJoin()
}