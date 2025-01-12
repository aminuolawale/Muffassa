package com.aminuolawale.muffassa.presentation.utils

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

class Utils {
    companion object {
        fun formatDate(millis: Long): String {
            val date = ZonedDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault())
            val today = ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault())
            if (date.toLocalDate() == today.toLocalDate()) {
                return "Today, " + SimpleDateFormat("HH:MM").format(millis)
            }
            return SimpleDateFormat("MMM d, yyy").format(millis)
        }
    }
}