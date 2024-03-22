package com.lukaslechner.coroutineusecasesonandroid.flows.exceptionHandlling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*


suspend fun main() = coroutineScope{
    flow{
        try{
            emit(1)
            // calling the emit function executes the code of the collect block.
            // since, emit is inside the try/catch block the error will be handled.
            // but in the case, it is violating the transparency rule 1
            // Exception Transparency rule 1 : - A downstream exception must always
            // be propagated to the collector.
            // But in our case, the exception is handled at the upstream.
        }
        catch(e: Exception){
            println("Catch exception in flow builder.")
        }
    }.collect{
        throw Exception("Exception in collect")
    }
}