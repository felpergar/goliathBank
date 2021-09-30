package felipe.pereira.goliathbank.domain.transactions

import felipe.pereira.goliathbank.data.common.ResultWrapper
import felipe.pereira.goliathbank.domain.transactions.model.Transaction

interface TransactionsRepository {

  suspend fun getTransactions(): ResultWrapper<List<Transaction>>
}