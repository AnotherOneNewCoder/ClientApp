package ru.zhogin.composeClientApp.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import ru.zhogin.composeClientApp.dto.Client
import ru.zhogin.composeClientApp.dto.GenderType
import ru.zhogin.composeClientApp.models.PhotoModel
import ru.zhogin.composeClientApp.repositories.ClientRepository
import javax.inject.Inject


private val emptyClient = Client(
    id = 0L,
    telNumber = "",
    name = "",
    surname = "",
    patronymicSurname = "",
    gender = GenderType.FEMALE,
    dateOfBirth = "",
)

@HiltViewModel
class ClientViewModule @Inject constructor(
    private val repository: ClientRepository
): ViewModel() {

    private val noAvatar = PhotoModel()
    private val _photo = MutableLiveData(noAvatar)
    val photo: LiveData<PhotoModel>
        get() = _photo

    val data = repository.data
    val editedClient = MutableLiveData(emptyClient)

    fun saveClient() {
        editedClient.value?.let { client ->
            viewModelScope.launch {
                try {
                    repository.saveClient(client)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        editedClient.value = emptyClient
    }

    fun newSaveClient(client: Client) {
        viewModelScope.launch {
            try {
                repository.saveClient(client)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun removeClientById(id: Long) = viewModelScope.launch {
        try {
            repository.removeById(id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun searchClient(searchQuery: String) = repository.searchClient(searchQuery).flowOn(Dispatchers.Default)

    fun setPhoto(uri: Uri?) {
        _photo.value = PhotoModel(uri)
    }
    fun clearData() {
        editedClient.value = emptyClient
    }
    fun clearPhoto() {
        _photo.value = noAvatar
    }

//    fun getClientById(id: Long) = repository.getClientById(id).flowOn(Dispatchers.Default)


    fun getClientById(id: Long): Client {

        viewModelScope.launch {
            try {
                 editedClient.value = repository.getClientById(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return editedClient.value!!
    }

}