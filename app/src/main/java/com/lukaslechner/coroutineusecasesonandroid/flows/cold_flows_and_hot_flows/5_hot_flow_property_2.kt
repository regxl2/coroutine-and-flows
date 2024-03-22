package com.lukaslechner.coroutineusecasesonandroid.flows.cold_flows_and_hot_flows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

// In sharedFlows, emissions are shared between all collectors
fun main(){
    val sharedFlow = MutableSharedFlow<Int>()
    val scope = CoroutineScope(EmptyCoroutineContext)

    scope.launch {
        repeat(5){
            val value = it+1
            println("SharedFlow emits: $value")
            // this print statement for each emitted is printed only once.
            // It means the emissions are shared between all collectors.
            sharedFlow.emit(value)
            delay(200)
        }
    }

    scope.launch{
        sharedFlow.collect{
            println("Collector1 collected $it")
        }
    }
    scope.launch{
        sharedFlow.collect{
            println("Collector2 collected $it")
        }
    }

    Thread.sleep(1500)
}