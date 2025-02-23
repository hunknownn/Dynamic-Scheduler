package com.example.dynamicscheduler

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.Instant

@Entity
class Schedule(
    time: Instant,
    content: String
) {
    @Id
    var id: Long = 0L

    val time = time
    val content = content
}