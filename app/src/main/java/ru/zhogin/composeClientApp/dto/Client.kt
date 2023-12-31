package ru.zhogin.composeClientApp.dto

data class Client(
    val id: Long,
    val telNumber: String,
    val photo: String? = null,
    val name: String,
    val surname: String,
    val patronymicSurname: String? = null,
    val dateOfBirth: String? = null,
    val gender: GenderType,
    val visits: List<String> = emptyList(),
    val works: List<String> = emptyList(),
    val prices: List<Long> = emptyList(),
    val tips: List<Long> = emptyList(),
    val durations: List<Long> = emptyList(),
    val notes: List<String> = emptyList(),
)


enum class GenderType {
    FEMALE, MALE
}