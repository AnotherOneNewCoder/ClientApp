package ru.zhogin.composeClientApp.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.zhogin.composeClientApp.dto.Client
import ru.zhogin.composeClientApp.services.TypeConverter

@Entity
data class ClientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val telNumber: String,
    val photo: String? = null,
    val name: String,
    val surname: String,
    val patronymicSurname: String? = null,
    val dateOfBirth: String? = null,
    @Embedded
    val gender: GenderTypeEmbedded,
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
        surname = surname,
        patronymicSurname = patronymicSurname,
        dateOfBirth = dateOfBirth,
        gender = gender.toDto(),
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
            surname = client.surname,
            patronymicSurname = client.patronymicSurname,
            dateOfBirth = client.dateOfBirth,
            gender = GenderTypeEmbedded.fromDto(client.gender),
            visits = TypeConverter.toJson(client.visits),
            works = TypeConverter.toJson(client.works),
            prices = TypeConverter.toJson(client.prices),
            durations = TypeConverter.toJson(client.durations),
            notes = TypeConverter.toJson(client.notes)
        )
    }
}

fun List<ClientEntity>.toClientList() = map(ClientEntity::toDto)
fun List<Client>.toClientEntity() = map(ClientEntity::fromDto)