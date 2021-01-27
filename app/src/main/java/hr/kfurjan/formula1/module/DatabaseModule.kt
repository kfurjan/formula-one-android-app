package hr.kfurjan.formula1.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hr.kfurjan.formula1.R
import hr.kfurjan.formula1.dao.Formula1Database
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            Formula1Database::class.java,
            context.getString(R.string.formula1_db)
        ).build()

    @Provides
    fun provideDriverDao(formula1Database: Formula1Database) = formula1Database.driverDao()

    @Provides
    fun provideCircuitDao(formula1Database: Formula1Database) = formula1Database.circuitDao()

    @Provides
    fun provideConstructorDao(formula1Database: Formula1Database) = formula1Database.constructorDao()

    @Provides
    fun provideSeasonDao(formula1Database: Formula1Database) = formula1Database.seasonDao()
}
