package com.lukaslechner.coroutineusecasesonandroid.playground.structuredconcurrency


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main()=runBlocking<Unit>{
    val time = measureTimeMillis {
        val parent = launch(Dispatchers.Default){
            launch{
                delay(1000)
                println("Child Coroutine 1 has completed")
            }
            launch {
                delay(1000)
                println("Child Coroutine 2 has completed")
            }
        }
        parent.join()
        println("Parent Coroutine has completed")
    }
    println("Total time taken: $time")
}