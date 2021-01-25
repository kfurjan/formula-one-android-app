package hr.algebra.formula1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import hr.algebra.formula1.factory.RepositoryFactory
import hr.algebra.formula1.model.Constructor
import hr.algebra.formula1.repository.ConstructorRepository

class ConstructorViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private val repository: ConstructorRepository = RepositoryFactory.getRepository(context)

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
        _constructors.addSource(repository.getConstructorsFilteredByName(name)) { constructors ->
            _constructors.value = constructors
        }

    fun filterConstructorsByNationality(nationality: String) =
        _constructors.addSource(repository.getConstructorsFilteredByNationality(nationality)) { constructors ->
            _constructors.value = constructors
        }
}
