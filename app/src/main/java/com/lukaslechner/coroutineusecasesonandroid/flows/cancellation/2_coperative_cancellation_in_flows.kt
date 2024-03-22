package com.lukaslechner.coroutineusecasesonandroid.flows.cancellation

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.math.BigInteger
import kotlin.coroutines.EmptyCoroutineContext

// Flow is cooperative means that after the cancellation of the flow, there should be
// no emitting of the values and no execution of the any code inside the collect operator after
// cancellation of the flow. No execution of any code is useful on those cases where you are performing
// the heavy calculation.
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

//private fun intFlow() = flow{
//    emit(1)
//    emit(2)
//    // in collect operator, we are cancelling the flow after the value of 2.
//    // But this flow is not cooperative because the flow is still executing code
//    // after the the cancellation of the flow and stops just before another emit call.
//    // and we know, the flow is cooperative if and only if the flow doesn't executes
//    // any code after calling cancel()
//    println("Calculation started...")
//    calculateFactorialOf(10)
//    println("Calculation ended...")
//    emit(3)
//}
private fun intFlow() = flow{
    emit(1)
    emit(2)
    currentCoroutineContext().ensureActive()
    // this makes the flow cooperative. It checks whether the flow has been cancelled. If it is
    // then the it throws CancellationException.
    println("Calculation started...")
    calculateFactorialOf(10)
    println("Calculation ended...")

    emit(3)
}

private fun calculateFactorialOf(number: Int):BigInteger{
    var factorial  = BigInteger.ONE
    for(i in 1..number){
        factorial *= i.toBigInteger()
    }
    return factorial
}