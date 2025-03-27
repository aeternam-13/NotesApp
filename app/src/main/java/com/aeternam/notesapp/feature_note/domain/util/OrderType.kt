package com.aeternam.notesapp.feature_note.domain.util

sealed class OrderType {
    data object Ascending : OrderType()
    data object Descending : OrderType()
}