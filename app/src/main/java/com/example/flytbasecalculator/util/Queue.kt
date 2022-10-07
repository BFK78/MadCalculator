package com.example.flytbasecalculator.util

import androidx.compose.runtime.Composable

data class Queue<T>(
    var size: Int = 0,
    val queue: MutableList<T> = mutableListOf()
) {

    fun enqueue(element: T) {
        queue.add(element)
        this.size ++
    }

    fun dequeue(): T {
        this.size --
        return queue.removeAt(0)
    }

    @Composable
    fun EachFor(action: @Composable (T) -> Unit) {
        for (element in queue) action(element)
    }

}
