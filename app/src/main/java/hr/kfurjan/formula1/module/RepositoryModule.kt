package hr.kfurjan.formula1.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.kfurjan.formula1.dao.model.CircuitDao
import hr.kfurjan.formula1.dao.model.ConstructorDao
import hr.kfurjan.formula1.dao.model.DriverDao
import hr.kfurjan.formula1.dao.model.SeasonDao
import hr.kfurjan.formula1.repository.CircuitRepository
import hr.kfurjan.formula1.repository.ConstructorRepository
import hr.kfurjan.formula1.repository.DriverRepository
import hr.kfurjan.formula1.repository.SeasonRepository

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesDriverRepository(driverDao: DriverDao) = DriverRepository(driverDao)

    @Provides
    fun providesCircuitRepository(circuitDao: CircuitDao) = CircuitRepository(circuitDao)

    @Provides
    fun providesConstructorRepository(constructorDao: ConstructorDao) =
        ConstructorRepository(constructorDao)

    @Provides
    fun providesSeasonRepository(seasonDao: SeasonDao) = SeasonRepository(seasonDao)
}
