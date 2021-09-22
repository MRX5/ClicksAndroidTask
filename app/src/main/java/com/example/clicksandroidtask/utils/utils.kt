package com.example.clicksandroidtask.utils

import com.example.clicksandroidtask.model.News
import java.text.SimpleDateFormat
import java.util.*

class utils {
    companion object {
        fun getTime(newsTime: String): String {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            format.timeZone = TimeZone.getTimeZone("UTC")
            val calendar = Calendar.getInstance()
            calendar.time = format.parse(newsTime)
            val dayInArabic = getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK))
            val monthInArabic = getMonth(calendar.get(Calendar.MONTH))
            val year = calendar.get(Calendar.YEAR)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val a = if (calendar.get(Calendar.HOUR_OF_DAY) >= 12) "م" else "ص"
            return "$dayInArabic $day $monthInArabic $year-$hour:$minute$a"
        }

        private fun getDayOfWeek(day: Int): String {
            return when (day) {
                Calendar.SATURDAY -> "السبت"
                Calendar.SUNDAY -> "الأحد"
                Calendar.MONDAY -> "الأثنين"
                Calendar.TUESDAY -> "الثلاثاء"
                Calendar.WEDNESDAY -> "الأربعاء"
                Calendar.THURSDAY -> "الخميس"
                Calendar.FRIDAY -> "الجمعة"
                else -> ""
            }
        }

        private fun getMonth(month: Int): String {
            return when (month) {
                Calendar.JANUARY -> "يناير"
                Calendar.FEBRUARY -> "فبراير"
                Calendar.MARCH -> "مارس"
                Calendar.APRIL -> "ابريل"
                Calendar.MAY -> "مايو"
                Calendar.JUNE -> "يوينو"
                Calendar.JULY -> "يوليو"
                Calendar.AUGUST -> "اغسطس"
                Calendar.SEPTEMBER -> "سبتمبر"
                Calendar.OCTOBER -> "اكتوبر"
                Calendar.NOVEMBER -> "نوفمبر"
                Calendar.DECEMBER -> "ديسمبر"
                else -> ""
            }
        }

        fun filter(newsList: List<News>, query: String): List<News> {
            val result = mutableListOf<News>()
            newsList.forEach { news ->
                if (news.title?.lowercase()?.contains(query) == true ||
                    news.source?.name?.lowercase()?.contains(query) == true) {
                    result.add(news)
                }
            }
            return result
        }
    }
}