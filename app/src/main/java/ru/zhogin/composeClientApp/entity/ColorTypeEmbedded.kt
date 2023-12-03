package ru.zhogin.composeClientApp.entity

import ru.zhogin.composeClientApp.dto.ColorType

data class ColorTypeEmbedded(
    val type: String
) {
    fun toDto() = ColorType.valueOf(type)

    companion object{
        fun fromDto(dto: ColorType) = ColorTypeEmbedded(dto.name)
    }
}
