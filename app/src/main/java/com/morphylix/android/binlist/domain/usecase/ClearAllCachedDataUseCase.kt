package com.morphylix.android.binlist.domain.usecase

import com.morphylix.android.binlist.domain.Repository
import javax.inject.Inject

class ClearAllCachedDataUseCase @Inject constructor(private val repository: Repository) {
    suspend fun execute() {
        repository.clearAllCachedData()
    }
}