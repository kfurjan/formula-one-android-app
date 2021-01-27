package hr.kfurjan.formula1.factory

import android.content.Context
import hr.kfurjan.formula1.repository.Repository

class RepositoryFactory {
    companion object {
        inline fun <reified T> getRepository(context: Context): T where T : Repository<*> =
            T::class.constructors.find { it.parameters.size == 1 }?.call(context)!!
    }
}
