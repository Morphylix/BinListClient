package com.morphylix.android.binlist.util

interface EntityMapper<T, R> {
    fun mapFromEntity(entity: T): R

    fun mapToEntity(model: R): T
}