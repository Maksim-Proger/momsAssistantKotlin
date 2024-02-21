package project.moms.assistant.model

import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.Calendar
import java.util.Locale

class DateTimeClass {

    fun currentDate() : String {
        val calendar: Calendar = Calendar.getInstance()
        val dateFormat: SimpleDateFormat = SimpleDateFormat("EEEE, dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(calendar.time).toString()
    }

    fun currentTime() : String {
        val localTime: LocalTime = LocalTime.now()
        val hours: Int = localTime.hour
        val minutes: Int = localTime.minute
        val seconds: Int = localTime.second
        return "$hours:$minutes:$seconds"
    }

}