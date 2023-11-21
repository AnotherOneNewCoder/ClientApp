package ru.zhogin.composeClientApp.dto

data class Client(
    val id: Long,
    val telNumber: String,
    val photo: String?,
    val name: String,
    val dateOfBirth: String,
    val visits: List<String> = emptyList(),
    val works: List<String> = emptyList(),
    val prices: List<Double> = emptyList(),
    val durations: List<Double> = emptyList(),
    val notes: List<String> = emptyList(),
)
