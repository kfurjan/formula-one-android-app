package hr.kfurjan.formula1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.kfurjan.formula1.model.Constructor
import hr.kfurjan.formula1.repository.ConstructorRepository
import javax.inject.Inject

@HiltViewModel
class ConstructorViewModel @Inject constructor(private val repository: ConstructorRepository) :
    ViewModel() {

    private val _constructors = MediatorLiveData<List<Constructor>>()
    private val constructors: LiveData<List<Constructor>>
        get() = _constructors

    init {
        getAllConstructors()
    }

    fun getConstructorsData() = constructors

    private fun getAllConstructors() =
        _constructors.addSource(repository.queryAll()) { constructors ->
            _constructors.value = constructors
        }

    fun filterConstructorsByName(name: String) =
        _constructors.addSource(
            repository.getConstructorsFilteredByName(name)
        ) { constructors ->
            _constructors.value = constructors
        }

    fun filterConstructorsByNationality(nationality: String) =
        _constructors.addSource(
            repository.getConstructorsFilteredByNationality(nationality)
        ) { constructors ->
            _constructors.value = constructors
        }
}
