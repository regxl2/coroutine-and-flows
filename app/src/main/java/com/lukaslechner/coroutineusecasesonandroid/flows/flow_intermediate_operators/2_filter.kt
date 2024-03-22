package com.lukaslechner.coroutineusecasesonandroid.flows.flow_intermediate_operators


import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOf

suspend fun main(){
    flowOf(1, 2, 3, 4, 5)
        .filter{ value -> value%2==1 }
        .collect{ emittedValue -> println(emittedValue) }
    // by using filter operator, we have filtered out the values of flow which are odd

    flowOf("Abhishek", "Gaurav", "Ashish", "Prasanjeet", "Rishab", null)
        .filterNotNull()
        .collect{ emittedValue -> println(emittedValue) }
    // by using filerNotNull operator, we can filter the values which are not null.

    flowOf<Any>(1, "A", 2, "B", 3, "C")
        .filterIsInstance<Int>()
        .collect{emittedValue -> println(emittedValue)}
    // by using filterIsInstance, we can filter out values based on their type.
    // for example, in this case, it is filter the values of Int type.
}