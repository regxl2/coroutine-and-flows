package com.lukaslechner.coroutineusecasesonandroid.exceptionhandling

import kotlinx.coroutines.*

fun main(){
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("Exception:$throwable")
    }
    val scope = CoroutineScope(Job() + exceptionHandler)
    val deferredJob = scope.async {
        println("Coroutine started")
        delay(200)
        throw RuntimeException()
    }
    scope.launch {
        deferredJob.await()
    }
    Thread.sleep(500)
}