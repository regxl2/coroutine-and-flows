package com.lukaslechner.coroutineusecasesonandroid.playground.cancellation

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//code1-
fun main() = runBlocking{
    val startTime = System.currentTimeMillis()
    println("Started")
    val parent = launch{
        timeMeasure(startTime)
        println("parent")
        Thread.sleep(500)
        timeMeasure(startTime)
        println("Hello world1 ")
        launch{
            timeMeasure(startTime)
            println("Hello world2 ")
            launch{
                timeMeasure(startTime)
                println("Hello world3")
            }
        }
    }
    timeMeasure(startTime)
    delay(200)
    // because of using Thread.sleep() inside the job . It is calculating 200ms in a weird way
    timeMeasure(startTime)
    println("cancelling parent")
    parent.cancel()
    timeMeasure(startTime)
}


