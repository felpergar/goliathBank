package felipe.pereira.goliathbank.data.repository.transactions.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import felipe.pereira.goliathbank.data.repository.transactions.datasource.local.model.TransactionLocalEntity

@Dao
interface TransactionDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun saveTransactions(transactions: List<TransactionLocalEntity>): List<Long>

  @Query("SELECT * FROM `transaction` where code = :code")
  fun getTransactionsByCode(code: String): List<TransactionLocalEntity>

  @Query("DELETE FROM `transaction`")
  fun deleteTransactions()}