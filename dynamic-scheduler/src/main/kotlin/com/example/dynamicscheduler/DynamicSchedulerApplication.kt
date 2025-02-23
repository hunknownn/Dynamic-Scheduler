package com.example.dynamicscheduler

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class DynamicSchedulerApplication

fun main(args: Array<String>) {
	runApplication<DynamicSchedulerApplication>(*args)
}
