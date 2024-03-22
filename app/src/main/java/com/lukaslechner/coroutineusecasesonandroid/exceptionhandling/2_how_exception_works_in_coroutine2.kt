package com.lukaslechner.coroutineusecasesonandroid.exceptionhandling

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch(Dispatchers.Default){
        try{
            launch {
                // In functions the exceptions are rethrown by the parent function if the exception occur in child function.
                // It will be caught by try/catch block
                // if parent calling functions has try/catch block, but it is not true in the case of coroutine.
                // It will be only caught if the block inside the child coroutine itself has try/catch block but
                // as shown in the the code outer try/catch will not handle exception because in coroutine the exception are
                // not rethrown by the parent coroutine if the exception occur in the child coroutine, it will propagated
                // to the parent coroutine (up in the hierarchy)..
                throw RuntimeException()
            }
        }
        catch(e: RuntimeException){
            println("Exception is caught: $e")
        }
    }
}