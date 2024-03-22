package com.lukaslechner.coroutineusecasesonandroid.playground.structuredconcurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Run coroutine concurrently
//fun main()= runBlocking<Unit> {
//    launch {
//        launch {
//            println("coroutine1 started")
//            delay(100)
//            println("coroutine1 ended")
//        }
//        launch {
//            println("coroutine2 started")
//            delay(200)
//            println("coroutine2 ended")
//        }
//        launch {
//            println("coroutine3 started")
//            delay(300)
//            println("coroutine3 ended")
//        }
//    }
//}


//short way to write the above code using extension function..
// if we use normal function then launch will show error because launch need CoroutineScope
// to start, therefore will be using extension function on CoroutineScope, so that launch can access the CoroutineScope
fun main() = runBlocking<Unit> {
    launch{
        doSomeWork(1, 100)
        doSomeWork(2, 200)
        doSomeWork(3, 300)
    }
}

fun CoroutineScope.doSomeWork(number: Int, time: Long){
    launch {
        println("coroutine$number started")
        delay(time)
        println("coroutine$number ended")
    }
}