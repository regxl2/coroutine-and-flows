package com.lukaslechner.coroutineusecasesonandroid.flows.cold_flows_and_hot_flows

import kotlinx.coroutines.*

// since, coroutineScope is a suspending block it will wait
// until the whole code inside the coroutineScope is not
// completed.
suspend fun main():Unit= coroutineScope{
    println("started")
    launch{
        println("Hello world")
    }
    println("ended")
}
