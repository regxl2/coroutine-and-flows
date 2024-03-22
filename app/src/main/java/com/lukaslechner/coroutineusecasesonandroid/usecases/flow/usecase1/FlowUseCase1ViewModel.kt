package com.lukaslechner.coroutineusecasesonandroid.usecases.flow.usecase1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber

class FlowUseCase1ViewModel(
    private val stockPriceDataSource: StockPriceDataSource
) : BaseViewModel<UiState>() {
//    val currentStockPriceAsLiveData: MutableLiveData<UiState> = MutableLiveData()
//    var job: Job? = null

    init {
//        solution1: -
//        viewModelScope.launch {
//            stockPriceDataSource.latestStockList.collect{ list ->
//                currentStockPriceAsLiveData.value = UiState.Success(list)
//            }
//        }

//        solution 2: Alternative and more readable code: -
//        stockPriceDataSource.latestStockList
//            .onEach { list ->
//                currentStockPriceAsLiveData.value = UiState.Success(list)
//            }
//            .launchIn(viewModelScope)
//    }

//        solution 3: Alternative and more readable code with lifeCycle operators
//        stockPriceDataSource.latestStockList
//            .map { list -> UiState.Success(list) as UiState }
//            .onStart { emit(UiState.Loading) }
//            .onEach { uiState ->
//                currentStockPriceAsLiveData.value = uiState
//            }
//            .launchIn(viewModelScope)
//    }
//    One of the main problem in the above code is that the values in the StockPriceDataSource will even make
//    request to the server even we put the app in the background. Since, we have used the while(true) loop.
//    Making unnecessary call to server is very bad practice even when the activity is not visible to the user.




//    solution4: -
//    fun startFlowCollection() {
//        job = stockPriceDataSource.latestStockList
//            .map { list -> UiState.Success(list) as UiState }
//            .onStart { emit(UiState.Loading) }
//            .onEach { uiState -> currentStockPriceAsLiveData.value = uiState }
//            .onCompletion { Timber.tag("Flow").d("Flow has completed") }
//            .launchIn(viewModelScope)
//    }
//
//    fun stopFlowCollection() {
//        job?.cancel()
//    }
//     By above job, Object we can easily cancel the request to the server by calling stopFlowCollection() method
//     in the onStop() lifecycle method of the activity. But the problem when the configuration will change then the flow
//     will get cancelled. So, this is optimal optimal solution as compared to the above solutions, but not the best solution.
    }

    //    Best solution making the flow as LiveData object:-
    val currentStockPriceAsLiveData: LiveData<UiState> = stockPriceDataSource.latestStockList
        .map { list -> UiState.Success(list) as UiState }
        .onStart { emit(UiState.Loading) }
        .onCompletion { Timber.tag("Flow").d("Flow has completed") }
        .asLiveData()
}
