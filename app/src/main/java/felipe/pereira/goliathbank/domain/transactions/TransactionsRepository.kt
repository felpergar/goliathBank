package felipe.pereira.goliathbank.domain.transactions

import felipe.pereira.goliathbank.data.common.ResultWrapper
import felipe.pereira.goliathbank.domain.transactions.model.Transaction
import felipe.pereira.goliathbank.domain.transactions.usecase.GetTransactionsByCodeParams

interface TransactionsRepository {

  suspend fun getTransactions(): ResultWrapper<List<Transaction>>

  suspend fun getTransactionsByCode(params: GetTransactionsByCodeParams): ResultWrapper<List<Transaction>>
}