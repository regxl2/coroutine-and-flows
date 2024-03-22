package com.lukaslechner.coroutineusecasesonandroid.flows.flowbuilders

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

suspend fun main(){
    val firstFlow = flowOf<Int>(1).collect{emittedValue ->
        println("First flow: $emittedValue")
    }

    val secondFlow = flowOf<Int>(1, 2, 3)
    secondFlow.collect{emittedValue ->
        println("Second flow: $emittedValue")
    }

    // we can also convert the list into flow using asFlow() function
    listOf<Int>(1, 2, 3).asFlow().collect{ emittedValue ->
        println("Third flow: $emittedValue")
    }

    // this below flow builder is flexible because we can put arbitrary code
    // inside the flow builder.
    flow{
        delay(2000)
        emit("The value is emitted after 2000ms")
    }.collect{ emittedValue ->
        println("Fourth flow: $emittedValue")
    }

    // Inside flow builder we can also emit the values from other flow
    flow{
        delay(2000)
        emit("The value is emitted after 2000ms")
//        secondFlow.collect{emittedValue ->
//            emit(emittedValue)
//        }
//        shorthand of the above code:-
        emitAll(secondFlow)
    }.collect{ emittedValue ->
        println("Fifth flow: $emittedValue")
    }
}