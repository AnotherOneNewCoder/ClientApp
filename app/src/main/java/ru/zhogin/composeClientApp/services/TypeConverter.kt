package ru.zhogin.composeClientApp.services

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object TypeConverter {
    private val gson = Gson()

    @androidx.room.TypeConverter
    fun <T>toJson(t: List<T>): String? {
        return if (t == null || t.isEmpty()) {
            null
        } else {
            gson.toJson(t)
        }
    }
    @androidx.room.TypeConverter
    fun <T>jsonToType(json: String?): List<T> {
        return if (json.isNullOrEmpty()) {
            listOf()
        } else {
            val type = object : TypeToken<List<T>>() {}.type
            gson.fromJson(json, type)
        }
    }
}