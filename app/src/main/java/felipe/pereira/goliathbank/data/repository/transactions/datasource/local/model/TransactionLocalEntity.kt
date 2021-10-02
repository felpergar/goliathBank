package felipe.pereira.goliathbank.data.repository.transactions.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import felipe.pereira.goliathbank.domain.transactions.model.Transaction

@Entity(tableName = "transaction")
class TransactionLocalEntity (
  @PrimaryKey val id: String,
  val code: String,
  val amount: String,
  val currency: String
)

private fun Transaction.transformToLocalEntity() = TransactionLocalEntity(System.currentTimeMillis().toString(), code, amount, currency)

fun List<Transaction>.transformToLocalEntity() = map { it.transformToLocalEntity() }

private fun TransactionLocalEntity.transformToDomain() = Transaction(code, amount, currency)

fun List<TransactionLocalEntity>.transformToDomain() = map { it.transformToDomain() }
