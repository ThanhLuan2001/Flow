package com.dogwithfunny.dogtraining.dognews.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    val coldFlow = flow {
        println("Cold Flow bắt đầu phát dữ liệu")
        for (i in 1..3) {
            delay(1000)
            emit(i)  // Phát dữ liệu
        }
    }

    println("Collector 1 bắt đầu thu thập dữ liệu")
    coldFlow.collect { println("Collector 1 nhận được: $it") }

    println("Collector 2 bắt đầu thu thập dữ liệu")
    coldFlow.collect { println("Collector 2 nhận được: $it") }
}
