package com.lukaslechner.coroutineusecasesonandroid.exceptionhandling

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun main(){
    val exceptionHandler = CoroutineExceptionHandler{_, throwable ->
        println("Exception is caught: $throwable")
    }
    val scope = CoroutineScope(Job())
    scope.launch {
        launch(exceptionHandler){ //The exceptionHandler is passed but
            // the exception is not handled because it is not the top level coroutine
            // and we know the exceptionHandler should be passed to the scope or the top
            // level coroutine.
            throw RuntimeException()
        }
    }
    Thread.sleep(1000)
}