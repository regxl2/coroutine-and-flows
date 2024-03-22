package com.lukaslechner.coroutineusecasesonandroid.flows.exceptionHandlling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*


suspend fun main() = coroutineScope{
    flow{
        try{
            emit(1)
            // calling the emit function executes the code of the collect block.
            // since, emit is inside the try/catch block the error will be handled.
        }
        catch(e: Exception){
            println("Catch exception in flow builder.")
            emit(2)
            // emit(2) call is causing the exception because it is violating
            // the transparency rule 2
            // Transparency rule 2 : - Once an uncaught exception was thrown
            // downstream, the flow isn't allowed to emit additional values.

            // in this case, uncaught exception was already thrown into collect operator
            // from catch we were emitting value. This is prohibited in order to avoid unspecified
            // behaviour. In order avoid this we can use catch operator.
        }
    }.collect{
        throw Exception("Exception in collect")
    }
}