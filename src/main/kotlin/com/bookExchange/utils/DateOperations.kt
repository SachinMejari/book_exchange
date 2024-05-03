package com.bookExchange.utils

import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Service
class DateOperations {
    fun getCurrentDateTime(): String {
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return currentDateTime.format(formatter)
    }

    fun getMonthBackDate(): String {
        val currentDateTime = LocalDateTime.now().minusDays(30)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return currentDateTime.format(formatter)
    }

    fun convertStringUTCToIST(utcTimeString: String): String {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") // Define your pattern
        val utcLocalDateTime: LocalDateTime = LocalDateTime.parse(utcTimeString, formatter)

        val utcZoneId: ZoneId = ZoneId.of("UTC")
        val utcZonedDateTime: ZonedDateTime = utcLocalDateTime.atZone(utcZoneId)

        val istZoneId: ZoneId = ZoneId.of("Asia/Kolkata")
        val istZonedDateTime: ZonedDateTime = utcZonedDateTime.withZoneSameInstant(istZoneId)

        val istFormatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z") // Define your IST pattern
        return istZonedDateTime.format(istFormatter)
    }
}