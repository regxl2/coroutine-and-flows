package com.lukaslechner.coroutineusecasesonandroid.exceptionhandling

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// code1 (doubt not cleared)-
// since, on passing exceptionHandler at coroutineScope is still causing
val exceptionHandler = CoroutineExceptionHandler{ _, throwable ->
    println("Exception is handled: $throwable")
}
//fun main() = runBlocking(exceptionHandler){
//    val job = launch(Dispatchers.Default){
//        throw RuntimeException()
//    }
//}

//code2(doubt resolved )-
// since, passing Job() object in the child coroutine start new Job hierarchy different from the parent.
// Child parent relationship is disturbed. It starts its own hierarchy(For better understanding learn about Job hierarchy)
// Team leader of Coroutine, recommend not to pass jobs in the context parameter to the
// coroutine builders in modern code
// Therefore, exceptionHandler will be now the top level coroutine. Therefore, it is cancelled.
fun main() = runBlocking {
    val job = launch{
        // since, this top level coroutine of CoroutineScope the exception will be handled.
        launch(Job() + exceptionHandler){
            println(Thread.currentThread().name)
            throw RuntimeException()
        }
    }
}