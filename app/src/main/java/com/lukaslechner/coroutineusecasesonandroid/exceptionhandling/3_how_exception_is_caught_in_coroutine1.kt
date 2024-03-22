package com.lukaslechner.coroutineusecasesonandroid.exceptionhandling

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("Exception is caught: $throwable")
    }
    // we can use CoroutineExceptionHandler to caught the uncaught exceptions.
    // Important:- CoroutineExceptionHandler should only be passed to the scope
    // or in its top level coroutine

//    case1 (passed to the scope)-
//    val job = CoroutineScope(Job() + exceptionHandler)
//    job.launch {
//        throw RuntimeException()
//    }

//    case2 (passed to top level coroutine)-
    val job = CoroutineScope(Job())
    job.launch(exceptionHandler){
        throw RuntimeException()
    }

    Thread.sleep(1000)
}