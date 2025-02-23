package com.example.dynamicscheduler

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler

@Configuration
class ScheduleConfig {

    @Bean(value = ["taskScheduler"])
    fun taskScheduler(): ThreadPoolTaskScheduler {
        return ThreadPoolTaskScheduler().apply {
            poolSize = 5 // 스레드 풀 크기 설정 (동시 실행 가능 작업 수)
            setThreadNamePrefix("Dynamic-Scheduler-")
            initialize()
        }
    }
}