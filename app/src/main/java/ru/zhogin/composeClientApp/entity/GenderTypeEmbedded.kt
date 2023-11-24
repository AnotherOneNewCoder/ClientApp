package ru.zhogin.composeClientApp.entity

import ru.zhogin.composeClientApp.dto.GenderType

data class GenderTypeEmbedded(
    val type: String
) {
    fun toDto() = GenderType.valueOf(type)

    companion object {
        fun fromDto(dto: GenderType) = GenderTypeEmbedded(dto.name)
    }
}