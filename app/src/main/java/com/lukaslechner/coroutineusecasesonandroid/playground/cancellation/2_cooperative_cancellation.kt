package com.lukaslechner.coroutineusecasesonandroid.playground.cancellation

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

//https://stackoverflow.com/questions/69097765/how-to-stop-or-cancel-a-kotlin-coroutine-stop-currently-running-code-block-inst

// this is demonstration of if i use inherited context and if i use Dispatchers.Default for cancellation

fun timeMeasure(time: Long){
    println(System.currentTimeMillis()-time)
}

// case 1 (using inherited context)-
//fun main()= runBlocking<Unit> {
//    val startTime = System.currentTimeMillis()
//    println(Thread.currentThread().name)
//    val job = launch{
//        repeat(10){
//            ensureActive()
//            println("Hello world${it}")
//            Thread.sleep(100)
//            println(Thread.currentThread().name)
//            timeMeasure(startTime)
//        }
//    }
////    delay(250)
////     if im not using the delay then directly job will be cancelled
////     control won't go to the job coroutine. Thus directly canceling coroutine.
//    println("cancelling the parent")
//    job.cancel()
//    timeMeasure(startTime)
//}

// case2 (using inherited context)-
//fun main()= runBlocking<Unit> {
//    val startTime = System.currentTimeMillis()
//    println(Thread.currentThread().name)
//    val job = launch{
//        repeat(10){
//            Thread.sleep(100)
//            println("Hello world${it}")
//            println(Thread.currentThread().name)
//            timeMeasure(startTime)
//        }
//    }
////    since , all coroutine is running on the main thread . It is calculating the 200ms delay in a weird way becausse of using thread.sleep()
////    therefore, it is not able to cancel the job
//    delay(200)
//    println("cancelling the parent")
//    job.cancel()
//    timeMeasure(startTime)
//}


//code3(using Dispatcher.Default) using ensureActive()-

//fun main()= runBlocking<Unit> {
//    val startTime = System.currentTimeMillis()
//    println(Thread.currentThread().name)
//    val job = launch(Dispatchers.Default){
//        repeat(10){
//            Thread.sleep(100)
//            ensureActive()
//            println("Hello world${it}")
//            println(Thread.currentThread().name)
//            timeMeasure(startTime)
//        }
//    }
//    delay(200)
//    println("cancelling the parent")
//    job.cancel()
//    timeMeasure(startTime)
//}


//code4(using Dispatcher.Default) using yield()-

//fun main()= runBlocking<Unit> {
//    val startTime = System.currentTimeMillis()
//    println(Thread.currentThread().name)
//    val job = launch(Dispatchers.Default){
//        repeat(10){
//            Thread.sleep(100)
//            yield()
////            Yields the thread (or thread pool) of the current coroutine dispatcher
////            to other coroutines on the same dispatcher to run if possible.
//            println("Hello world${it}")
//            println(Thread.currentThread().name)
//            timeMeasure(startTime)
//        }
//    }
//    delay(200)
//    println("cancelling the parent")
//    job.cancel()
//    timeMeasure(startTime)
//}



//code5(using Dispatcher.Default) using isActive-

fun main()= runBlocking<Unit> {
    val startTime = System.currentTimeMillis()
    println(Thread.currentThread().name)
    val job = launch(Dispatchers.Default){
        repeat(10){
            if(!isActive){
                println("cleaning up...")
                throw CancellationException()
                // when we call job.cancel() then isActive becomes false
                // on checking the condition we must throw CancellationException
                // to cancel the coroutine.
                // In short, in order to cancel the coroutine we must throw CancellationException.
            }
            Thread.sleep(100)
            println("Hello world${it}")
            println(Thread.currentThread().name)
            timeMeasure(startTime)
        }
    }
    delay(200)
    println("cancelling the parent")
    job.cancel()
    timeMeasure(startTime)
}


