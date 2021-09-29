package felipe.pereira.goliathbank.domain.transactions.usecase

import felipe.pereira.goliathbank.data.common.ResultWrapper
import felipe.pereira.goliathbank.domain.UseCaseAsync
import felipe.pereira.goliathbank.domain.transactions.TransactionsRepository
import felipe.pereira.goliathbank.domain.transactions.model.Transactions

class GetTransactions(
  val repository: TransactionsRepository
): UseCaseAsync<Unit, List<Transactions>> {

  override suspend fun buildAsync(params: Unit): ResultWrapper<List<Transactions>> = repository.getTransactions()
}