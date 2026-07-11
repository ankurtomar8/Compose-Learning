package com.example.composelearning

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CounterViewModel : ViewModel() {
    // 1. Private mutable state - only this ViewModel can modify the data
    private val _count = MutableStateFlow(0)

    // 2. Public immutable state - the UI can read this but cannot modify it directly
    val count: StateFlow<Int> = _count.asStateFlow()

    // 3. Events/Actions
    fun incrementCounter() {
        _count.value++
    }

    fun decrementCounter() {
        if (_count.value > 0) {
            _count.value--
        }
    }

    fun resetCounter() {
        _count.value = 0
    }
}