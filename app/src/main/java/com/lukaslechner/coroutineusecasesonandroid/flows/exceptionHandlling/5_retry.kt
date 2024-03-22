package com.lukaslechner.coroutineusecasesonandroid.flows.exceptionHandlling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException

suspend fun main():Unit= coroutineScope{
    launch{
        stocksFlow()
            // By default the retries value is infinite
            .retry(retries = 3){ throwable ->
                // retry is used in those cases where if network request is failed due to internet or many other
                //  reasons then IOException or Network Exception is thrown but You want to other retry or many retries
                // then we use retry operator.
                println("Enter retry with $throwable")
                delay(1000)
                // this last line tells if the throwable is IOException then return true for retry.
                throwable is IOException
            }
            .catch{ throwable ->
                println("Handle exception in catch operator: $throwable")
            }
            .collect{ stockData ->
                println("CollectData: $stockData")
            }
    }
}

private fun stocksFlow() = flow{
    repeat(5){ index ->
        delay(1000)
        if(index < 4){
            emit("New stock data")
        }
        else{
            throw IOException("Network request failed...")
        }
    }
}
//    .retryWhen { cause, attempt ->
//        println("Inside retryWhen: This is $attempt attempt with $cause")
//        delay(1000* (attempt+1))
//        // this similar to the retry but the difference is in block you can access nth attempt value inside the block.
//        // and you can limit the number retries in this block, it will retry infinite times.
//        cause is IOException
//    }