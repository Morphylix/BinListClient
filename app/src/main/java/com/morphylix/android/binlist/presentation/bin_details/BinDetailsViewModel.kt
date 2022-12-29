package com.morphylix.android.binlist.presentation.bin_details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morphylix.android.binlist.domain.model.domain.CardBinData
import com.morphylix.android.binlist.domain.usecase.CacheBinDataUseCase
import com.morphylix.android.binlist.domain.usecase.FetchBinDataUseCase
import com.morphylix.android.binlist.domain.usecase.LoadCachedCardBinDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "BinDetailsViewModel"

class BinDetailsViewModel
@Inject constructor(
    private val fetchBinDataUseCase: FetchBinDataUseCase,
    private val cacheBinDataUseCase: CacheBinDataUseCase,
    private val loadCachedCardBinDataUseCase: LoadCachedCardBinDataUseCase
) : ViewModel() {

    private val _loadingState: MutableStateFlow<LoadingState> =
        MutableStateFlow(LoadingState.Loading)
    val loadingState: StateFlow<LoadingState>
        get() = _loadingState

    fun loadCardBinData(bin: String) {
        var cardBinData: CardBinData?
        viewModelScope.launch {
            try {
                cardBinData = loadCachedCardBinDataUseCase.execute(bin)
                if (cardBinData == null) {
                    cardBinData = fetchBinDataUseCase.execute(bin)
                }
                cardBinData!!.bin = bin
                _loadingState.value = LoadingState.Success(cardBinData)
                cacheCardBinData(cardBinData!!)
            } catch (e: Exception) {
                _loadingState.value = LoadingState.Error(e)
            }
        }
    }

    private fun cacheCardBinData(cardBinData: CardBinData) {
        viewModelScope.launch {
            cacheBinDataUseCase.execute(cardBinData)
        }
    }

}