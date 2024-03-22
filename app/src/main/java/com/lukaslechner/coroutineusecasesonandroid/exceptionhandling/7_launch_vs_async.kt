package com.lukaslechner.coroutineusecasesonandroid.exceptionhandling

import kotlinx.coroutines.*
// code1 (launch): -
// As we know if an error occurs the exception is propagated upward in the hierarchy.
//fun main(){
//    val scope = CoroutineScope(Job())
//    val job = scope.launch{
//        delay(200)
//        throw RuntimeException()
//    }
//    Thread.sleep(500)
//}

// code2 (async if we not call await): -
// In async, the if an exception occurs the exception is propagated up in the hierarchy
// but the but the exception will no be thrown until we call await() function on the
// deferredJob

//fun main(){
//    val scope = CoroutineScope(Job())
//
//    val deferredJob = scope.async{
//        println("coroutine is started")
//        delay(200)
//        throw RuntimeException()
//    }
//    Thread.sleep(500)
//}

//code3 (async with await): -

fun main(){
    val scope = CoroutineScope(Job())

    val deferredJob = scope.async{
        println("coroutine is started")
        delay(200)
        throw RuntimeException()
    }
    scope.launch{
        deferredJob.await()
    }
    Thread.sleep(500)
}