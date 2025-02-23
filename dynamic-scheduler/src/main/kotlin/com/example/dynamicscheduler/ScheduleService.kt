package com.example.dynamicscheduler

import org.springframework.stereotype.Service
import java.time.Instant

@Service
class ScheduleService(
    private val scheduleRepository: ScheduleRepository,
    private val scheduler: Scheduler
) {
    fun schedule(eventTime: Instant) {
        scheduler.scheduleOnce(eventTime)
    }
}