package com.lukaslechner.coroutineusecasesonandroid.exceptionhandling

import kotlinx.coroutines.*

// code1(exception in top level scope of the supervisorScope): -
// if we throw exception top level scope of supervisorScope the exception will be handled by the
// try/catch block but if the exception occur in the child coroutine of the supervisorScope
// the outer try/catch will not catch exception the exception will be propagated up the hierarchy.
// Since, the supervisorScope start its own job hierarchy independent of the parent coroutine
// In supervisorScope error handling works as same as simple coroutine, exception are propagated up the hierarchy.
//fun main()= runBlocking<Unit>{
//    try {
//        supervisorScope {
//            throw RuntimeException()
//            // top level scope exception will be caught in try/catch
//        }
//    }
//    catch (e: RuntimeException){
//        println("Exception: $e")
//    }
//}

//code2(exception in the child level scope of the supervisorScope): -
//fun main()= runBlocking<Unit>{
//    try {
//        supervisorScope {
//            launch {
//                throw RuntimeException()
//                // exception will not be caught in the try/catch block
//                // but it will be propagated up the hierarchy
//            }
//        }
//    }
//    catch (e: RuntimeException){
//        println("Exception: $e")
//    }
//}


//code3(exception in the child level scope of the supervisorScope): -
fun main()= runBlocking<Unit>{
    try {
        val exceptionHandler = CoroutineExceptionHandler{_, throwable ->
            println("Exception caught by exceptionHandler: $throwable")
        }
        supervisorScope{
            launch(exceptionHandler){
                // since, this the top level coroutine and exceptionHandler should be passed to
                // top level coroutine or the CoroutineScope itself to handle the exception.
                launch {
                    throw RuntimeException()
                    // exception will not be caught in the try/catch block
                    // but it will be propagated up the hierarchy
                }
            }
            launch {
                println("Coroutine started")
                delay(100)
                println("Coroutine completed")
            }
        }
    }
    catch (e: RuntimeException){
        println("Exception caught by try/catch: $e")
    }
}