package com.lukaslechner.coroutineusecasesonandroid.flows.flowbasics

import com.lukaslechner.coroutineusecasesonandroid.playground.utils.printWithTimePassed
import java.math.BigInteger

fun main(){
    val num = 5
    val startTime = System.currentTimeMillis()
    val result = calculateFactorialOf2(num).forEach{
        printWithTimePassed(it, startTime)
        // when all the items are added to the list then the items started printed.
        // Therefore, we observe no difference of 10ms in time when each item is printed.
    }
}

// Here, we can see we can only return one item that is list from the function but list can contain multiple values.
// we can also see, if we are using list then, from forEach we cannot print when each item was added
// After adding all the item then we can loop in the list to print the item.
// If we want from forEach we want to print the item when it is added then, we will use Sequence
// In this function value is return once
fun calculateFactorialOf2(num: Int): List<BigInteger> = buildList {
    var result = BigInteger.ONE
    for( i in 1..num){
        Thread.sleep(10)
        result = result.multiply(i.toBigInteger())
        add(result)
    }
}