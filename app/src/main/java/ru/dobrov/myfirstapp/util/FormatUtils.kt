package ru.dobrov.myfirstapp.util

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object FormatUtils {
    fun formatCount(count: Int): String {
        return when {
            count >= 1_000_000 -> {
                val millions = count / 1_000_000.0
                if (millions % 1.0 == 0.0)
                    "${millions.toInt()}M"
                else
                    DecimalFormat(".").format(millions) + "M"
            }
            count >= 10_000 -> {
                "${count / 1000}K"
            }
            count >= 1_000 -> {
                val thousands = count / 1000.0
                if (thousands % 1.0 == 0.0)
                    "${thousands.toInt()}K"
                else
                    DecimalFormat(".").format(thousands) + "K"
            }
            else -> count.toString()
        }
    }
    fun formatDate(date: Date): String {
        val format = SimpleDateFormat("d MMM в HH:mm", Locale("ru"))
        return format.format(date)
    }
}