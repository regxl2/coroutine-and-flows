package com.lukaslechner.coroutineusecasesonandroid.playground.cancellation

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() = runBlocking<Unit>{
    val job = launch{
        repeat(10){
            if(isActive){
                println("Hello World")
                Thread.sleep(100)
            }
            else{
                // if isActive will become false we should throw Exception
                // but since we are using withContext(NonCancellable) the code inside it
                // will never throw cancellationException
                withContext(NonCancellable){
                    delay(100)
                    println("cleaning")
                    throw CancellationException()
                }
            }
        }
    }
}