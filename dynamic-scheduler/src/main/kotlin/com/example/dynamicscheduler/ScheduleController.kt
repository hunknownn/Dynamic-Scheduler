package com.example.dynamicscheduler

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@RequestMapping("/api")
class ScheduleController(
    private val scheduleService: ScheduleService
) {

    @GetMapping("/schedule")
    fun dynamicScheduler(
        @RequestParam("eventTime") eventTime: Instant,
    ) {
        scheduleService.schedule(eventTime)
    }
}