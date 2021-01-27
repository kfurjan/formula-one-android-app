package hr.kfurjan.formula1.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hr.kfurjan.formula1.api.Formula1DataFetcher
import hr.kfurjan.formula1.repository.CircuitRepository
import hr.kfurjan.formula1.repository.ConstructorRepository
import hr.kfurjan.formula1.repository.DriverRepository
import hr.kfurjan.formula1.repository.SeasonRepository

@Module
@InstallIn(SingletonComponent::class)
object DataFetcherModule {

    @Provides
    fun providesFormula1DataFetcher(
        @ApplicationContext context: Context,
        driverRepository: DriverRepository,
        circuitRepository: CircuitRepository,
        seasonRepository: SeasonRepository,
        constructorRepository: ConstructorRepository
    ) = Formula1DataFetcher(
        context,
        driverRepository,
        circuitRepository,
        seasonRepository,
        constructorRepository
    )
}
