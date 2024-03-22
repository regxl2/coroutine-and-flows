package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase14

import com.lukaslechner.coroutineusecasesonandroid.mock.AndroidVersion
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AndroidVersionRepository(
    private var database: AndroidVersionDao,
    private val scope: CoroutineScope,
    private val api: MockApi = mockApi()
) {

    suspend fun getLocalAndroidVersions(): List<AndroidVersion> {
        return database.getAndroidVersions().mapToUiModelList()
    }

    suspend fun loadAndStoreRemoteAndroidVersions(): List<AndroidVersion> {
        return emptyList()
    }

    fun clearDatabase() {
        scope.launch{
            database.clear()
        }
        // when leaving the screen we want the the database to clear, if we will use the viewModelScope then
        // on leaving the screen the onCleared method of the viewModel will be called then coroutine will be cancelled
        // and data will not be cleared. Therefore, we are using our custom scope which is associated with application's
        // lifecycle
    }
}