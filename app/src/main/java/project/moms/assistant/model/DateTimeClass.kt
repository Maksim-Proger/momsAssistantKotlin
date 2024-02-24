package project.moms.assistant.model

import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Locale


class DateTimeClass {

    fun currentDate() : String {
        val calendar: Calendar = Calendar.getInstance()
        val dateFormat: SimpleDateFormat = SimpleDateFormat("EEEE, dd.MM.yyyy",
            Locale.getDefault())
        return dateFormat.format(calendar.time).toString()
    }

    fun currentTime() : String {
        val localTime: LocalTime = LocalTime.now()
        val timeFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        return localTime.format(timeFormat)
    }

    fun timeDifference(time1: String, time2: String) : String {
        val localTime1: LocalTime = LocalTime.parse(time1)
        val localTime2: LocalTime = LocalTime.parse(time2)
        val differenceInMinutes = ChronoUnit.MINUTES.between(localTime1, localTime2)
        return "$differenceInMinutes минут"
    }

}