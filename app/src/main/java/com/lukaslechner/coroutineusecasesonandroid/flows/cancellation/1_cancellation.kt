package com.lukaslechner.coroutineusecasesonandroid.flows.cancellation

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.EmptyCoroutineContext

// Flow is cooperative means that after the cancellation of the flow, there should be
// no emitting of the values and no execution of the any code inside the collect operator after
// cancellation of the flow.
suspend fun main(){
    val scope = CoroutineScope(EmptyCoroutineContext)

    scope.launch {
        intFlow()
            .onCompletion { throwable ->
                if(throwable is CancellationException){
                    println("Flow got cancelled.")
                }
            }
            .collect{
                println("Collected: $it")
                if(it == 2){
                    cancel()
                }
            }
    }.join()
}

private fun intFlow() = flow{
    emit(1)
    emit(2)
    emit(3)
}