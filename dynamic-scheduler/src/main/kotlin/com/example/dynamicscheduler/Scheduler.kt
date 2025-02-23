package com.example.dynamicscheduler

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.*
import java.util.*

@Component
class Scheduler(
    @Qualifier(value = "taskScheduler")
    private val taskScheduler: TaskScheduler
) {
    @Scheduled(fixedRate = 5000) // 5초마다 실행 (이전 작업이 끝나든 말든 일정 간격 유지)
    fun fixedRateTask() {
        println("Fixed rate task 실행 - ${System.currentTimeMillis()}")
    }

    fun scheduleOnce(eventTime: Instant) {
        taskScheduler.schedule(::loadContent, eventTime)
    }

    fun scheduleDaily(eventTime: Instant) {
        val period = Duration.ofDays(1)
        taskScheduler.scheduleAtFixedRate(::loadContent, eventTime, period)

        val future = taskScheduler.schedule({
            ::loadContent
            // 다음 실행 날짜 갱신 후 다시 예약
            scheduleDaily(eventTime)
        }, eventTime)
    }

    fun scheduleWeekly(eventTime: Instant) {

    }

    fun scheduleMonthly(dayOfMonth: Int, eventTime: LocalTime) {
        val now = LocalDateTime.now()
        var nextRun = now.withDayOfMonth(dayOfMonth)
            .withHour(eventTime.hour)
            .withMinute(eventTime.minute)
            .withSecond(eventTime.second)

        if (now.isAfter(nextRun)) {
            nextRun = nextRun.plusMonths(1)
        }

        val future = taskScheduler.schedule({
            ::loadContent
            // 다음 실행 날짜 갱신 후 다시 예약
            scheduleMonthly(dayOfMonth, eventTime)
        }, nextRun.atZone(ZoneId.systemDefault()).toInstant())
    }

    fun loadContent() = println("running dynamic schedule")
}