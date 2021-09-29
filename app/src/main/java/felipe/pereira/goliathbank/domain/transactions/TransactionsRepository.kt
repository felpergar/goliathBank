package felipe.pereira.goliathbank.domain.transactions

import felipe.pereira.goliathbank.data.common.ResultWrapper
import felipe.pereira.goliathbank.domain.transactions.model.Transactions

interface TransactionsRepository {

  suspend fun getTransactions(): ResultWrapper<List<Transactions>>
}