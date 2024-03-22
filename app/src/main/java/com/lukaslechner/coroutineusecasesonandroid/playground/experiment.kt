package com.lukaslechner.coroutineusecasesonandroid.playground

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

//code1-
fun main() = runBlocking<Unit>{
    coroutineScope {
        println("Thread: ${Thread.currentThread().name}")
        launch {
            println("Thread: ${Thread.currentThread().name}")
        }
    }
}




