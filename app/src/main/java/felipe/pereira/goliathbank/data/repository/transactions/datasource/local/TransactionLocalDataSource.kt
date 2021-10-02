package felipe.pereira.goliathbank.data.repository.transactions.datasource.local

import felipe.pereira.goliathbank.data.common.getSafeResult
import felipe.pereira.goliathbank.data.repository.transactions.datasource.local.model.transformToDomain
import felipe.pereira.goliathbank.data.repository.transactions.datasource.local.model.transformToLocalEntity
import felipe.pereira.goliathbank.domain.transactions.model.Transaction

class TransactionLocalDataSource(
  private val dao: TransactionDao
) {

  suspend fun saveTransactions(transactions: List<Transaction>) = getSafeResult {
    dao.deleteTransactions()
    dao.saveTransactions(transactions.transformToLocalEntity())
  }

  suspend fun getTransactionByProduct(code: String) = getSafeResult { dao.getTransactionsByCode(code).transformToDomain() }
}