package ru.zhogin.composeClientApp.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.zhogin.composeClientApp.dao.ClientDao
import ru.zhogin.composeClientApp.dto.Client
import ru.zhogin.composeClientApp.entity.ClientEntity
import ru.zhogin.composeClientApp.entity.toClientList
import javax.inject.Inject

class ClientRepositoryImpl @Inject constructor(
    private val clientDao: ClientDao,
): ClientRepository {
    override val data: Flow<List<Client>> = clientDao.getAll()
        .map { it.toClientList() }
        .flowOn(Dispatchers.Default)


    override fun searchClient(searchQuery: String): Flow<List<Client>> = clientDao.searchUser(searchQuery)
        .map { it.toClientList() }
        .flowOn(Dispatchers.Default)

    override suspend fun removeById(id: Long) {
        try {
            clientDao.removeByID(id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun saveClient(client: Client) {
        try {
            clientDao.save(ClientEntity.fromDto(client))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}