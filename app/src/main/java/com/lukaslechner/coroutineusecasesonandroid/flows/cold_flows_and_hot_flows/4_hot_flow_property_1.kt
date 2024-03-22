package com.lukaslechner.coroutineusecasesonandroid.flows.cold_flows_and_hot_flows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

// SharedFlows are active regardless of whether there are collectors.
// It stays active even when there is no more collectors. It means the emissions of sharedFlows are independent of collections.
fun main(){
    val sharedFlow = MutableSharedFlow<Int>()
    val scope = CoroutineScope(EmptyCoroutineContext)

    scope.launch {
        repeat(5){
            val value = it+1
            println("SharedFlow emits: $value")
            sharedFlow.emit(value)
            delay(200)
        }
    }

    Thread.sleep(500)

    scope.launch{
        sharedFlow.collect{
            println("Collected $it")
        }
        // values starts collecting from 4
        // if there were coldFlows then emitting of values only starts
        // when there is a collector and all the values from the start were collected
    }
    Thread.sleep(1500)
}