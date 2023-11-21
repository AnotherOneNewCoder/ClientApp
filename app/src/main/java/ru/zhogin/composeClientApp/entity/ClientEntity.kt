package ru.zhogin.composeClientApp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.zhogin.composeClientApp.dto.Client
import ru.zhogin.composeClientApp.services.TypeConverter

@Entity
data class ClientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val telNumber: String,
    val photo: String?,
    val name: String,
    val dateOfBirth: String,
    val visits: String?,
    val works: String?,
    val prices: String?,
    val durations: String?,
    val notes: String?
) {
    fun toDto() = Client(
        id = id,
        telNumber = telNumber,
        photo = photo,
        name = name,
        dateOfBirth = dateOfBirth,
        visits = TypeConverter.jsonToType(visits),
        works = TypeConverter.jsonToType(works),
        prices = TypeConverter.jsonToType(prices),
        durations = TypeConverter.jsonToType(durations),
        notes = TypeConverter.jsonToType(notes)
    )

    companion object{
        fun fromDto(client: Client): ClientEntity = ClientEntity(
            id = client.id,
            telNumber = client.telNumber,
            photo = client.photo,
            name = client.name,
            dateOfBirth = client.dateOfBirth,
            visits = TypeConverter.toJson(client.visits),
            works = TypeConverter.toJson(client.works),
            prices = TypeConverter.toJson(client.prices),
            durations = TypeConverter.toJson(client.durations),
            notes = TypeConverter.toJson(client.notes)
        )
    }
}