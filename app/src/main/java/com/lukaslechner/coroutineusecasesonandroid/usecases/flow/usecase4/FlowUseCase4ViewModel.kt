package com.lukaslechner.coroutineusecasesonandroid.usecases.flow.usecase4

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber

class FlowUseCase4ViewModel(
    stockPriceDataSource: StockPriceDataSource
) : BaseViewModel<UiState>() {

//    val currentStockPriceAsLiveData: Flow<UiState> = stockPriceDataSource
//        .latestStockList
//        .map { stockList ->
//            UiState.Success(stockList) as UiState
//        }
//        .onStart {
//            emit(UiState.Loading)
//        }
//        .onCompletion {
//            Timber.tag("Flow").d("Flow has completed.")
//        }
//    Problems of using flow (Naive Approach):-
//    1. Flow Producer continue to run when the app is in background.
//    2. Activity receives emissions and renders UI even when the app is in background.
//    3. Multiple collectors create multiple flows. In this each individual flow collector will make its request.
//    4. Configuration Change re-starts the flow.

    // Solution to problem 1 and 2
    // is by storing the coroutine job into variable and override activity lifecycle onStart() to coroutine
    // and onStop() to start the coroutine
    // we can also use repeatOnLifecycle() to avoid boilerplate of the overriding the onStart() and onStop() lifecycle method
    // of the activity.

    // But problem 3 and 4 still remains.

    // problem3 could be solved using sharedFlows because in sharedFlows the
    // flow is shared among the multiple flows, in this case the network request will be shared among flow collectors

    //problem4 could be solved using passing timeout value in SharingStarted.WhileSubscribed(timeout= 5000)
    // timeout = 5000ms is considered as a good value for configuration changes and by default the timeout value is 0ms. Timeout value means that wait for 5000ms
    // for new collectors before stopping the upstream collection.

    // But the problem 5 arises, after the configuration change the blank screen is shown, this is because last emitted value is collected by old activity
    // when activity is recreated then activity starts collecting but there is gap of time for next emission. Therefore, the blank is screen is visible.
    // To solve this problem, we must display last emitted value for that gap of time.

    // problem 5, could be solved by passing replay value equal to 1 (It means last emitted value will emitted to the new collector, in our case,
    // new activity will collect the last emitted value). replay defines how many emissions
    // replayed or in other terms remitted when new collector starts collecting form our sharedFlow.
    // by default the replay value is 0.
//    val currentStockPriceAsLiveData: Flow<UiState> = stockPriceDataSource
//        .latestStockList
//        .map { stockList ->
//            UiState.Success(stockList) as UiState
//        }
//        .onStart {
//            emit(UiState.Loading)
//        }
//        .onCompletion {
//            Timber.tag("Flow").d("Flow has completed.")
//        }
//        .shareIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L), replay = 1)


    //    since, stateflow is special and more optimized version of sharedFlow for observing UI. We will be using stateFlow instead sharedFlow.
    val currentStockPriceAsLiveData: Flow<UiState> = stockPriceDataSource
        .latestStockList
        .map{stockList ->
            UiState.Success(stockList) as UiState
        }
        .onCompletion {
            Timber.tag("Flow").d("Flow has completed.")
        }
        .stateIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(5000L), initialValue= UiState.Loading)

}