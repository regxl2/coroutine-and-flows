package com.lukaslechner.coroutineusecasesonandroid.flows.flowbasics

import com.lukaslechner.coroutineusecasesonandroid.playground.utils.printWithTimePassed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.math.BigInteger

fun main() = runBlocking{
    val num = 5
    val startTime = System.currentTimeMillis()
    launch {
        // collect is suspending call therefore it needs to be run inside coroutine, otherwise it will block the runBlocking coroutine
        // collect is similar to forEach loop used in the sequence
        val result = calculateFactorialOf4(num).collect{
            printWithTimePassed(it, startTime)
        }
    }
    println("Ready for the work")
    // since, flow asynchronous the println statement will be before calculateFactorialOf4() function
}

// By using Flow, we can return the values continuously and also suspending.
fun calculateFactorialOf4(num: Int): Flow<BigInteger> = flow {
    var result = BigInteger.ONE
    for( i in 1..num){
        delay(10) // flow is asynchronous data stream, we can use delay
        result = result.multiply(i.toBigInteger())
        emit(result) // it just similar to the yield used in the sequence
    }
}.flowOn(Dispatchers.Default) // flowOn() is used to decide in which thread you want to execute the flow