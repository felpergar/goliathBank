package felipe.pereira.goliathbank.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import felipe.pereira.goliathbank.data.repository.currencyrates.datasource.local.CurrencyRateDao
import felipe.pereira.goliathbank.data.repository.currencyrates.datasource.local.model.CurrencyRateLocalEntity
import felipe.pereira.goliathbank.data.repository.transactions.datasource.local.TransactionDao
import felipe.pereira.goliathbank.data.repository.transactions.datasource.local.model.TransactionLocalEntity

@Database(
  entities = [
    TransactionLocalEntity::class,
    CurrencyRateLocalEntity::class
  ],
  version = 1
)
abstract class GoliathDataBase : RoomDatabase() {

  abstract fun getTransactionDataBase(): TransactionDao

  abstract fun getCurrencyRateDataBase(): CurrencyRateDao

  companion object {
    fun buildDatabase(context: Context) =
      Room.databaseBuilder(context, GoliathDataBase::class.java, "GoliathDataBase")
        .fallbackToDestructiveMigration()
        .build()
  }
}

