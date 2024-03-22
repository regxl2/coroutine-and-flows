package com.lukaslechner.coroutineusecasesonandroid.playground.cancellation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.math.BigInteger

//https://kotlinlang.org/docs/cancellation-and-timeouts.html#cancelling-coroutine-execution
// Here, I am not using delay function inside repeat() function and it is not canceling the job
//However, if a coroutine is working in a computation and does not check for cancellation,
//then it cannot be cancelled, like the following example shows:
//fun main()= runBlocking{
//    var fact = BigInteger.ONE;
//    val parent = launch(Dispatchers.Default){
//        var i =1
//        while(i<=50000){
//            fact= fact.multiply(i.toBigInteger())
//            i++
//        }
//        println(fact)
//    }
//    delay(200)
//    println("cancelling the parent")
//    parent.cancel()
//}

fun main()= runBlocking{
    var fact = BigInteger.ONE;
    val parent = launch(Dispatchers.Default){
        var i =1
        while(i<=50000){
            ensureActive()
            fact= fact.multiply(i.toBigInteger())
            i++
        }
        println(fact)
    }
    delay(200)
    println("cancelling the parent")
    parent.cancel()
}