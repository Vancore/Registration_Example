package com.example.registrationzkb.utils

import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class Utils {
    companion object {

        fun minimumCalendarTime(): Date {
            val minimumCalendarTime = Calendar.getInstance()
            minimumCalendarTime[Calendar.YEAR] = 1900
            minimumCalendarTime[Calendar.MONTH] = Calendar.JANUARY
            minimumCalendarTime[Calendar.DAY_OF_MONTH] = 1
            return minimumCalendarTime.time
        }

        fun maximumCalendarTime(): Date {
            val maximumCalendarTime = Calendar.getInstance()
            maximumCalendarTime[Calendar.YEAR] = 2021
            maximumCalendarTime[Calendar.MONTH] = Calendar.DECEMBER
            maximumCalendarTime[Calendar.DAY_OF_MONTH] = 31
            return maximumCalendarTime.time
        }

        val EMAIL_ADDRESS_CUSTOM_PATTERN: Pattern = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{1,25}" +
                    ")+"
        )

        fun specificDateToCalendar(year: Int, month: Int, day: Int): Date {
            val maximumCalendarTime = Calendar.getInstance()
            maximumCalendarTime[Calendar.YEAR] = year
            maximumCalendarTime[Calendar.MONTH] = month
            maximumCalendarTime[Calendar.DAY_OF_MONTH] = day
            return maximumCalendarTime.time
        }

        fun convertDateToString(time: Long): String {
            val date = Date(time)
            val format = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            return format.format(date)
        }
    }
}