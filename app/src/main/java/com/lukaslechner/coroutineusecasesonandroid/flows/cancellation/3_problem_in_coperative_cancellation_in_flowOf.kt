package com.lukaslechner.coroutineusecasesonandroid.flows.cancellation

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.math.BigInteger
import kotlin.coroutines.EmptyCoroutineContext

// Flow is cooperative means that after the cancellation of the flow, there should be
// no emitting of the values and no execution of the any code inside the collect operator after
// cancellation of the flow.
suspend fun main(){
    val scope = CoroutineScope(EmptyCoroutineContext)

    scope.launch {
        // flowOf builder operator internally doesn't check for cancellation.
        // Therefore, it will not be cancelled, to make it cancellable and cooperative
        // we must use OnEach operator and manually make it cancellable
        flowOf(1, 2, 3)
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
