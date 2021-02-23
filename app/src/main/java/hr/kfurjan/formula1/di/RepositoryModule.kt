package hr.kfurjan.formula1.di

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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDriverRepository(driverDao: DriverDao) = DriverRepository(driverDao)

    @Provides
    @Singleton
    fun provideCircuitRepository(circuitDao: CircuitDao) = CircuitRepository(circuitDao)

    @Provides
    @Singleton
    fun provideConstructorRepository(constructorDao: ConstructorDao) =
        ConstructorRepository(constructorDao)

    @Provides
    @Singleton
    fun provideSeasonRepository(seasonDao: SeasonDao) = SeasonRepository(seasonDao)
}
