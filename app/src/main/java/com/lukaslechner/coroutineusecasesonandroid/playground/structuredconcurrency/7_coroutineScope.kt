package com.lukaslechner.coroutineusecasesonandroid.playground.structuredconcurrency

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

//if you want coroutine3 to start after the coroutine1 and 2 has completed concurrently
//fun main()= runBlocking{
//    val parent = launch(Dispatchers.Default){
//        val coroutine1 = launch{
//            println("coroutine1 started")
//            delay(100)
//            println("coroutine1 ended")
//        }
//        val coroutine2 = launch{
//            println("coroutine2 started")
//            delay(200)
//            println("coroutine2 ended")
//        }
//        coroutine1.join()
//        coroutine2.join()
//        val coroutine3 = launch{
//            println("coroutine3 started")
//            delay(300)
//            println("coroutine3 ended")
//        }
//    }
//}

//There is alternative and better of writing the above code by using scoping functions :- coroutineScope, supervisorScope,withContext
//fun main() = runBlocking<Unit>{
//    launch(Dispatchers.Default){
//        coroutineScope {
//            launch{
//                println("coroutine1 started")
//                delay(100)
//                println("coroutine1 ended")
//            }
//            launch{
//                println("coroutine2 started")
//                delay(200)
//                println("coroutine2 ended")
//            }
//        }
//        launch{
//            println("coroutine3 started")
//            delay(300)
//            println("coroutine3 ended")
//        }
//    }
//}

//another way to write above code
fun main() = runBlocking<Unit>{
    launch(Dispatchers.Default){
        doSomeWork()
        launch{
            println("coroutine3 started")
            delay(300)
            println("coroutine3 ended")
        }
    }
}

suspend fun doSomeWork()= coroutineScope {
    launch{
        println("coroutine1 started")
        delay(100)
        println("coroutine1 ended")
    }
    launch{
        println("coroutine2 started")
        delay(200)
        println("coroutine2 ended")
    }
}


