package felipe.pereira.goliathbank.domain.transactions.usecase

import felipe.pereira.goliathbank.data.common.ResultWrapper
import felipe.pereira.goliathbank.domain.UseCaseAsync
import felipe.pereira.goliathbank.domain.transactions.TransactionsRepository
import felipe.pereira.goliathbank.domain.transactions.model.Transaction

class GetTransactions(
  val repository: TransactionsRepository
): UseCaseAsync<Unit, List<Transaction>> {

  override suspend fun buildAsync(params: Unit): ResultWrapper<List<Transaction>> = repository.getTransactions()
}