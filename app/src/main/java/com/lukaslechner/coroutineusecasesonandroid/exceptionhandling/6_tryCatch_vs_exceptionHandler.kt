package com.lukaslechner.coroutineusecasesonandroid.exceptionhandling

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


// code1 (try/catch): -
// if in coroutine block is handled in the try/catch block, even if the error occur
// the siblings will not be cancelled and exception will not be propagated upward.
// However, the structure concurrency will not be followed.

//fun main() = runBlocking<Unit>{
//    val scope = CoroutineScope(Job())
//    val job= scope.launch {
//        println("Thread name: ${Thread.currentThread().name}")
//        launch {
//            println("coroutine1 started")
//            delay(1000)
//            try{
//                throw RuntimeException()
//            }
//            catch (e: RuntimeException){
//                println("Exception: $e")
//            }
//        }
//        launch {
//            println("coroutine2 started")
//            delay(3000)
//            println("coroutine2 completed")
//        }
//    }
//    Thread.sleep(5000)
//}


// code2 (coroutineExceptionHandler): -
// if the launch the coroutine is fails the all the siblings and child coroutines will cancelled
// and the exception will be propagated upward. In this case, the structured concurrency will be followed.

fun main() = runBlocking<Unit>{
    val exceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        println("Exception :$throwable")
    }
    val scope = CoroutineScope(Job() +exceptionHandler)

    val job = scope.launch{
        scope.launch {
            println("Coroutine1 started")
            delay(1000)
            throw RuntimeException()
        }
        scope.launch {
            println("Coroutine2 started")
            delay(3000)
            println("Coroutine2 completed")
        }
    }
    job.join()
    Thread.sleep(5000)
}