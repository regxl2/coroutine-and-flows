package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase13

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import com.lukaslechner.coroutineusecasesonandroid.mock.VersionFeatures
import kotlinx.coroutines.*
import retrofit2.HttpException
import timber.log.Timber

class ExceptionHandlingViewModel(
    private val api: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun handleExceptionWithTryCatch() {
        uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val result = api.getAndroidVersionFeatures(27)
            } catch (e: Exception) {
                uiState.value = UiState.Error("Network request failed: $e")
            }
        }
    }

    fun handleWithCoroutineExceptionHandler() {
        uiState.value = UiState.Loading
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            uiState.value = UiState.Error("Network request failed: $throwable")
        }
        viewModelScope.launch(exceptionHandler) {
            api.getAndroidVersionFeatures(27)
        }
    }

    fun showResultsEvenIfChildCoroutineFails() {
        uiState.value = UiState.Loading
        viewModelScope.launch {
            supervisorScope {
                val dJob1 = async {
                    api.getAndroidVersionFeatures(27)
                }
                val dJob2 = async {
                    api.getAndroidVersionFeatures(28)
                }
                val dJob3 = async {
                    api.getAndroidVersionFeatures(29)
                }

                val result =
                    listOf<Deferred<VersionFeatures>>(dJob1, dJob2, dJob3).mapNotNull { job ->
                        try {
                            job.await()
                        } catch (e: HttpException) {
                            Timber.tag("Hello").e("showResultsEvenIfChildCoroutineFails: $e")
                            null
                        }
                        catch (e: CancellationException){
                            throw e
                        }
                    }
                uiState.value = UiState.Success(result)
            }
        }
    }
}