package hr.kfurjan.formula1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.kfurjan.formula1.model.Season
import hr.kfurjan.formula1.repository.SeasonRepository
import javax.inject.Inject

@HiltViewModel
class SeasonViewModel @Inject constructor(private val repository: SeasonRepository) : ViewModel() {

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
