package ru.zhogin.composeClientApp.dto

data class ClientInfo(
    val visit: String ,
    val work: String,
    val prices: Double,
    val tips: Double,
    val durations: Double,
    val notes: String,
)
