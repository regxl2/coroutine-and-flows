package com.lukaslechner.coroutineusecasesonandroid.flows.flow_intermediate_operators


import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

suspend fun main(){
    val flow1 = flowOf(1, 2, 3, 4, 5)
        .map { value -> "Emission $value" }
        .collect{ emittedValue -> println(emittedValue) }
    // by using map operator, we have converted the Flow<Int> into Flow<String>

    val flow2 = flowOf("Abhishek", "Gaurav", "Ashish", "Prasanjeet", "Rishab", null)
        .mapNotNull { value -> if(value.equals("Rishab")) null else "My name is $value" }
        .collect{ emittedValue -> println(emittedValue) }
    // by using mapNotNull operator, during mapping values if any value results in null value
    // then it will be ignored
}