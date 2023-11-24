package ru.zhogin.composeClientApp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.zhogin.composeClientApp.entity.ClientEntity

@Dao
interface ClientDao {
    @Query("SELECT * FROM ClientEntity ORDER BY id DESC")
    fun getAll(): Flow<List<ClientEntity>>

    suspend fun save(client: ClientEntity) =
        if (client.id == 0L) insert(client) else updateClientByID(client.id, client.dateOfBirth, client.name, client.durations, client.prices,client.photo,
            client.notes, client.telNumber, client.visits, client.works)

    @Insert
    suspend fun insert(client: ClientEntity)

    @Query(
        """
           UPDATE ClientEntity SET
               dateOfBirth = :dateOfBirth,
               name = :name,
               durations = :durations,
               prices = :prices,
               photo = :photo,
               notes = :notes,
               telNumber = :telNumber,
               visits = :visits,
               works = :works
           WHERE id = :id
        """
    )
    suspend fun updateClientByID(id: Long, dateOfBirth: String?, name: String, durations: String?, prices: String?, photo: String?, notes: String?,
                                 telNumber: String, visits: String?, works: String?)


    @Query("DELETE FROM ClientEntity WHERE id = :id")
    suspend fun removeByID(id: Long)
    @Query("DELETE FROM ClientEntity")
    suspend fun removeAll()

    @Query("SELECT * from ClientEntity WHERE id = :id")
    suspend fun getByID(id: Long) : ClientEntity

    @Query("SELECT COUNT(*) from ClientEntity WHERE id = :id")
    suspend fun getCount(id: Long): Int

    @Query("SELECT * FROM ClientEntity WHERE id LIKE :searchQuery OR name LIKE :searchQuery OR telNumber LIKE :searchQuery")
    fun searchUser(searchQuery: String): Flow<List<ClientEntity>>
}