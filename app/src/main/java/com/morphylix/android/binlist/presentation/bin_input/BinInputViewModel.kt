package com.morphylix.android.binlist.presentation.bin_input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morphylix.android.binlist.domain.model.domain.CardBinData
import com.morphylix.android.binlist.domain.usecase.ClearAllCachedDataUseCase
import com.morphylix.android.binlist.domain.usecase.GetCachedBinDataListUseCase
import com.morphylix.android.binlist.presentation.bin_details.LoadingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BinInputViewModel
@Inject constructor(
    private val getCachedBinDataListUseCase: GetCachedBinDataListUseCase,
    private val clearAllCachedDataUseCase: ClearAllCachedDataUseCase
) : ViewModel() {

    private val _loadingState: MutableStateFlow<LoadingState> =
        MutableStateFlow(LoadingState.Loading)
    val loadingState: StateFlow<LoadingState>
        get() = _loadingState

    fun getCachedCardList() {
        viewModelScope.launch {
            _loadingState.value = LoadingState.Success(getCachedBinDataListUseCase.execute())
        }
    }

    fun clearAllCachedData() {
        viewModelScope.launch {
            clearAllCachedDataUseCase.execute()
            _loadingState.value = LoadingState.Success(listOf<CardBinData>())
        }
    }
}