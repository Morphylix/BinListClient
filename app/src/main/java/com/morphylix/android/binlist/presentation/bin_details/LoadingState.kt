package com.morphylix.android.binlist.presentation.bin_details

sealed interface LoadingState {

    object Sleeping: LoadingState

    object Loading: LoadingState

    class Success<T>(val data: T): LoadingState

    class Error(val exception: Exception): LoadingState
}