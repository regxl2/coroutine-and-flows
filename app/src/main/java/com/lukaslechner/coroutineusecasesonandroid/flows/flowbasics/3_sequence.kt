package com.lukaslechner.coroutineusecasesonandroid.flows.flowbasics

import com.lukaslechner.coroutineusecasesonandroid.playground.utils.printWithTimePassed
import java.math.BigInteger

fun main(){
    val num = 5
    val startTime = System.currentTimeMillis()
    val result = calculateFactorialOf3(num).forEach{
        printWithTimePassed(it, startTime)
        // By using sequence we observe difference of 10ms in the time when each item is printed
    }
    println("Ready for the work")
}

// If from forEach we want to print the item when it is added then, we will use Sequence
// Sequence is synchronous data stream because when we called the forEach, println statement is not printed
// it is executed after forEach loop. It means Thread was when blocked when forEach was executing.
// Therefore, Sequence is the synchronous data stream.
// By using Sequence, we can return the values continuously but blocking.
fun calculateFactorialOf3(num: Int): Sequence<BigInteger> = sequence {
    var result = BigInteger.ONE
    for( i in 1..num){
        Thread.sleep(10)
        result = result.multiply(i.toBigInteger())
        yield(result)
    }
}