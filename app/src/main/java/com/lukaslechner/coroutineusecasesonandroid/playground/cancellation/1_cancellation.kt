package com.lukaslechner.coroutineusecasesonandroid.playground.cancellation

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.math.BigInteger

fun main() = runBlocking {
    val job = launch {
        repeat(10) { index ->
            println("operation number is $index")
            try {
                delay(100)
//                This suspending function is cancellable.
//                If the Job of the current coroutine is cancelled or completed while this suspending function is waiting,
//                this function immediately resumes with CancellationException.
                 //If there was no delay function inside repeat loop, then cancellationException would have not caused and repeat would have
                // finished totally
            } catch (e: CancellationException) {
                println("Cancellation exception was thrown")
                throw CancellationException()
            }
        }
    }
    delay(250)
    println("Cancelling the coroutine")
    job.cancel()
}
