package felipe.pereira.goliathbank.domain.transactions.usecase

import felipe.pereira.goliathbank.data.common.ResultWrapper
import felipe.pereira.goliathbank.domain.UseCaseAsync
import felipe.pereira.goliathbank.domain.transactions.TransactionsRepository
import felipe.pereira.goliathbank.domain.transactions.model.Transaction

class GetTransactionsByCode(
  private val repository: TransactionsRepository
): UseCaseAsync<GetTransactionsByCodeParams, List<Transaction>> {

  override suspend fun buildAsync(params: GetTransactionsByCodeParams): ResultWrapper<List<Transaction>> =
    repository.getTransactionsByCode(params)
}

class GetTransactionsByCodeParams(val code: String)