package com.morphylix.android.binlist.domain.usecase

import com.morphylix.android.binlist.domain.Repository
import com.morphylix.android.binlist.domain.model.domain.CardBinData
import javax.inject.Inject

class LoadCachedCardBinDataUseCase @Inject constructor(private val repository: Repository) {
    suspend fun execute(bin: String): CardBinData? {
        return repository.loadCachedBinData(bin)
    }
}