package com.dogwithfunny.dogtraining.dognews.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/*🟢 StateFlow (Dữ liệu trạng thái - Giữ giá trị mới nhất)

Đặc điểm
✅ Luôn lưu trữ giá trị mới nhất.
✅ Khi có collector mới tham gia, nó nhận giá trị hiện tại ngay lập tức.
✅ Thường dùng để lưu trạng thái UI (ViewModel trong MVVM).

Ví dụ dễ hiểu
Bạn có một bảng thông tin số dư tài khoản:

Nếu có ai vào xem, họ luôn thấy số dư mới nhất.
Nếu số dư thay đổi, mọi người đang theo dõi đều thấy ngay lập tức.*/

fun main() = runBlocking {
    val stateFlow = MutableStateFlow(0) // Giá trị mặc định là 0

    launch {
        for (i in 1..5) {
            delay(1000)
            stateFlow.value = i // Cập nhật giá trị mới
            println("🔥 Giá trị mới được cập nhật: $i")
        }
    }

    delay(2500) // Giả sử Collector 1 tham gia sau 2.5s
    println("Collector 1 tham gia")
    val job1 = launch { stateFlow.collect { println("Collector 1 nhận: $it") } }

    delay(2000) // Giả sử Collector 2 tham gia sau 2s nữa
    println("Collector 2 tham gia")
    val job2 = launch { stateFlow.collect { println("Collector 2 nhận: $it") } }

    delay(3000)
    job1.cancel()
    job2.cancel()
}

/*
✅ Khi nào dùng StateFlow?

Lưu trạng thái UI trong ViewModel (ví dụ: trạng thái mạng, số điểm trò chơi, tên người dùng).
Khi bạn chỉ quan tâm đến giá trị mới nhất, không cần giữ lịch sử.*/
