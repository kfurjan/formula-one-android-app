package hr.kfurjan.formula1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import hr.kfurjan.formula1.factory.RepositoryFactory
import hr.kfurjan.formula1.model.Season
import hr.kfurjan.formula1.repository.SeasonRepository

class SeasonViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private val repository: SeasonRepository = RepositoryFactory.getRepository(context)

    private val _seasons = MutableLiveData<String>()
    private lateinit var seasons: LiveData<List<Season>>

    init {
        getSeasonsFromRepository()
        filterSeasonsByYear("")
    }

    fun getSeasonsData() = seasons

    private fun getSeasonsFromRepository() {
        seasons = Transformations.switchMap(_seasons) { year ->
            repository.getSeasonsFilteredByYear(year)
        }
    }

    fun filterSeasonsByYear(year: String) =
        this._seasons.postValue(year)
}
