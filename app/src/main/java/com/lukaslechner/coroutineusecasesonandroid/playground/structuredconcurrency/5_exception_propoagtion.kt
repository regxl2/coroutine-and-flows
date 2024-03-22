package com.lukaslechner.coroutineusecasesonandroid.playground.structuredconcurrency

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

fun main(){
    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("caught exception:$throwable")
    }
    val scope = CoroutineScope(SupervisorJob() + coroutineExceptionHandler)
    // if we are using superViserJob then, if child job fails then parent coroutine will not be cancelled.
    // if we will use simple job the, if child job fails then parent coroutine will also be cancelled.
    scope.launch{
        launch {
            println("coroutine1 started")
            delay(50)
            println("coroutine1 fails")
            throw RuntimeException()
        }
        launch {
            println("coroutine2 started ${Thread.currentThread().name}")
            delay(500)
            println("coroutine2 completed")
        }.invokeOnCompletion {
            throwable ->
            println("coroutine2 got cancelled: $throwable")
        }
    }
    Thread.sleep(1000)
    println("is parent active: ${scope.isActive}")
}