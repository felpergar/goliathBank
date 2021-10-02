package felipe.pereira.goliathbank.data.repository.currencyrates.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import felipe.pereira.goliathbank.data.repository.currencyrates.datasource.local.model.CurrencyRateLocalEntity

@Dao
interface CurrencyRateDao {

  @Query("SELECT * FROM rate")
  suspend fun getCurrencyRates(): List<CurrencyRateLocalEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun saveCurrencyRates(rates: List<CurrencyRateLocalEntity>): List<Long>

  @Query("DELETE FROM rate")
  suspend fun deleteDataBase()
}