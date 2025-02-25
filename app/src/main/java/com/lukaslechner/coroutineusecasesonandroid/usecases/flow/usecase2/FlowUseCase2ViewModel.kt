package com.lukaslechner.coroutineusecasesonandroid.usecases.flow.usecase2

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.usecases.flow.mock.Stock
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import timber.log.Timber

class FlowUseCase2ViewModel(
    stockPriceDataSource: StockPriceDataSource,
    defaultDispatcher: CoroutineDispatcher
) : BaseViewModel<UiState>() {

    /*

    Flow exercise 1 Goals
        1) only update stock list when Alphabet(Google) (stock.name ="Alphabet (Google)") stock price is > 2300$
        2) only show stocks of "United States" (stock.country == "United States")
        3) show the correct rank (stock.rank) within "United States", not the world wide rank
        4) filter out Apple  (stock.name ="Apple") and Microsoft (stock.name ="Microsoft"), so that Google is number one
        5) only show company if it is one of the biggest 10 companies of the "United States" (stock.rank <= 10)
        6) stop flow collection after 10 emissions from the dataSource
        7) log out the number of the current emission so that we can check if flow collection stops after exactly 10 emissions
        8) Perform all flow processing on a background thread

     */
    val currentStockPriceAsLiveData: LiveData<UiState> = stockPriceDataSource
        .latestStockList
        .take(10)
        .withIndex()
        .onEach { Timber.tag("Flow").d("Iteration number: ${it.index + 1}") }
        .map { indexedValue -> indexedValue.value }
        .filter { list ->
            val googlePrice = list.find { stock -> stock.name == "Alphabet (Google)" }?.currentPrice
                ?: return@filter false
            googlePrice > 2300
        }
        .map { list ->
            var count = 1
            val newList = mutableListOf<Stock>()
            for(stock in list){
                if(stock.country == "United States" && stock.name != "Apple" && stock.name != "Microsoft" && count<=10){
                    val newStock = stock.copy(rank=count)
                    newList.add(newStock)
                    ++count
                }
            }
            newList.toList()
        }
        .map { list -> UiState.Success(list) as UiState }
        .onStart { emit(UiState.Loading) }
        .asLiveData(defaultDispatcher)
}