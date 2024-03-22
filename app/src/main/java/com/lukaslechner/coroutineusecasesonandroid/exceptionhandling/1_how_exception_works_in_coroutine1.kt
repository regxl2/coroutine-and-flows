package com.lukaslechner.coroutineusecasesonandroid.exceptionhandling

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch(Dispatchers.Default){
        // if you using try/catch block inside the launch scope where you think
        // error might occur then exception will be handled well.
        try{
            throw RuntimeException()
        }
        catch(e: RuntimeException){
            println("Exception is caught: $e")
        }
    }
}