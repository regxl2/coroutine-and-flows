package com.lukaslechner.coroutineusecasesonandroid.exceptionhandling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit>{
    try{
        coroutineScope {
            launch {
                launch {
                    throw RuntimeException()
                }
            }
        }
    }
    catch(e: RuntimeException){
        println("Exception: $e")
    }
}

// As, we know in simple coroutine try/catch will only works with current coroutine not
// with the child coroutine exception, exception is propagated upwards
// but by using coroutineScope you can handle the child coroutine exception
// with try/catch because the coroutineScope rethrows the exception of the child coroutine.