package ru.zhogin.composeClientApp.services

object MyUtils {
    fun priceToDouble(
        value: Long
    ): Double {

        return (value.toDouble()) / 100
    }

    fun priceToLong(
        value: Double
    ): Long {

        return (value * 100).toLong()
    }

    fun durationToMinute(
        value: Double
    ): Long {
        return (value * 60).toLong()
    }

    fun durationToHour(
        value: Long
    ): Double {
        return (value.toDouble()) / 60
    }

}