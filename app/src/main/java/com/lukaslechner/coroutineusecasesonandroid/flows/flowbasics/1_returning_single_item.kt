package com.lukaslechner.coroutineusecasesonandroid.flows.flowbasics

import java.math.BigInteger

fun main(){
    val num = 5
    val result = calculateFactorialOf1(5)
    println("Factorial of $num is $result")
}

// Here, we can see we can only return the only one value from the function
// In this value is returned once.
fun calculateFactorialOf1(num: Int): BigInteger {
    var result = BigInteger.ONE
    for( i in 1..num){
        Thread.sleep(100)
        result *= (i.toBigInteger())
    }
    return result
}
