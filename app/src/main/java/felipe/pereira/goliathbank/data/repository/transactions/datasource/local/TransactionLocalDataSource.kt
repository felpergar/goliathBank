package felipe.pereira.goliathbank.data.repository.transactions.datasource.local

import felipe.pereira.goliathbank.data.repository.transactions.datasource.local.model.transformToLocalEntity
import felipe.pereira.goliathbank.domain.transactions.model.Transaction

class TransactionLocalDataSource(
  private val dao: TransactionDao
) {

  suspend fun saveTransactions(transactions: List<Transaction>) {
    dao.saveTransactions(transactions.transformToLocalEntity())
  }

  fun getTransactionByProduct(code: String) = dao.getTransactionsByCode(code)
}