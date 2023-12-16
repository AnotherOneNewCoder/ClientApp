package ru.zhogin.composeClientApp.dto

data class ClientInfo(
    val visit: String ,
    val work: String,
    val prices: Long,
    val tips: Long,
//    val durations: Long,
    val notes: String,
)
