package com.ahrijksmuseum.presentation.utils

import androidx.lifecycle.Observer
import junit.framework.Assert.assertEquals

class TestableObserver<T> : Observer<T> {
    private val _history: MutableList<T> = mutableListOf()
    val history: List<T> = _history

    override fun onChanged(value: T) {
        _history.add(value)
    }

    fun assertAllEmitted(values: List<T>) {
        assertEquals(values.count(), _history.count())

        _history.forEachIndexed { index, t ->
            assertEquals(values[index], t)
        }
    }
}
