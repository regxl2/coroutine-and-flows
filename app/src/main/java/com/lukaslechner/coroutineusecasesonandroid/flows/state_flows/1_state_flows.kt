package com.lukaslechner.coroutineusecasesonandroid.flows.state_flows

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// State flow is a special - purpose, high-performance, and efficient implementation
// of SharedFlow for the narrow, but widely used case of sharing a state.
// StateFlow always has an initial value, replays one most recent value to new
// subscribers, does not buffer any more values, but keeps the last emitted one,
// does not support resetReplayCache.

// difference between sharedFlow and stateFlow
// 1. sharedFlow has no initial value whereas, stateFlow has initial value.
// 2. sharedFlow has customizable value whereas, stateFlow has fixed size of 1.
// 3. sharedFlow emits the subsequent equal values, whereas, stateFlow does not
//    emits subsequent equal values, stateFlow is sharedFlow with distinctUntilChanged operator

// Rule of Thumb of Usage:
// 1. Whenever you want to use a hot flow, use a stateFlow by default.
// 2. StateFlows are more efficient when used for state.
// 3. StateFlows provide convenient option to read and write its value in a
//    non-suspending fashion by synchronously accessing the .value property
//    sharedFlow we have to use .update operator in CoroutineScope to update its value.
// 4. Only if you have special requirements, switch to s SharedFlow.

suspend fun main(){
    val counter = MutableStateFlow(0)
    println("Initial value: ${counter.value}")
    coroutineScope {
        repeat(10_000){
            launch{
//                counter.value += 1
//                directly updating the counter value using counter.value is not thread safe,
//                Therefore, the value end value after operation is not equal to 10000.
//                To update counter value thread safe we must use update function.
                counter.update { currentValue ->
                    currentValue +1
                }
            }
        }
    }
    println("End value after operation: ${counter.value}")
}