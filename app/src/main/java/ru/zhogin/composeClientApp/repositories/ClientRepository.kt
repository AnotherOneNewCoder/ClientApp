package ru.zhogin.composeClientApp.repositories


import kotlinx.coroutines.flow.Flow
import ru.zhogin.composeClientApp.dto.Client

interface ClientRepository {
    val data: Flow<List<Client>>

    fun searchClient(searchQuery: String): Flow<List<Client>>
    suspend fun removeById(id: Long)
    suspend fun saveClient(client: Client)
}