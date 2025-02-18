package com.dogwithfunny.dogtraining.dognews.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/*🔴 SharedFlow (Luồng chia sẻ - Giữ nhiều giá trị)
Đặc điểm
✅ Có thể phát nhiều giá trị liên tục.
✅ Có thể giữ lịch sử (replay).
✅ Không có giá trị mặc định (khác với StateFlow).

Ví dụ dễ hiểu
Bạn có một nhóm chat:

Khi có tin nhắn mới, tất cả thành viên đang online đều nhận được.
Người mới tham gia không thấy tin nhắn cũ (trừ khi bạn cài đặt replay).*/

fun main() = runBlocking {
    val sharedFlow = MutableSharedFlow<Int>(replay = 2) // Giữ 2 giá trị gần nhất

    launch {
        for (i in 1..5) {
            delay(1000)
            sharedFlow.emit(i) // Phát giá trị mới
            println("🔥 Giá trị mới được phát: $i")
        }
    }

    delay(3500) // Giả sử Collector 1 tham gia sau 3.5s
    println("Collector 1 tham gia")
    val job1 = launch { sharedFlow.collect { println("Collector 1 nhận: $it") } }

    delay(2000) // Giả sử Collector 2 tham gia sau 2s nữa
    println("Collector 2 tham gia")
    val job2 = launch { sharedFlow.collect { println("Collector 2 nhận: $it") } }

    delay(3000)
    job1.cancel()
    job2.cancel()
}

/*✅ Khi nào dùng SharedFlow?

Khi bạn muốn phát sự kiện cho nhiều người cùng lúc (ví dụ: thông báo hệ thống, tin nhắn chat).
Khi bạn muốn lưu trữ một số giá trị gần đây (dùng replay).*/

