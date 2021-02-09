package hr.kfurjan.formula1.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hr.kfurjan.formula1.BuildConfig
import hr.kfurjan.formula1.api.Formula1DataApi
import hr.kfurjan.formula1.api.Formula1DataFetcher
import hr.kfurjan.formula1.repository.CircuitRepository
import hr.kfurjan.formula1.repository.ConstructorRepository
import hr.kfurjan.formula1.repository.DriverRepository
import hr.kfurjan.formula1.repository.SeasonRepository
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun providesRetrofit(BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideFormula1Api(retrofit: Retrofit): Formula1DataApi =
        retrofit.create(Formula1DataApi::class.java)

    @Provides
    fun provideFormula1DataFetcher(
        @ApplicationContext context: Context,
        driverRepository: DriverRepository,
        circuitRepository: CircuitRepository,
        seasonRepository: SeasonRepository,
        constructorRepository: ConstructorRepository,
        formula1DataApi: Formula1DataApi
    ) = Formula1DataFetcher(
        context,
        driverRepository,
        circuitRepository,
        seasonRepository,
        constructorRepository,
        formula1DataApi
    )
}
