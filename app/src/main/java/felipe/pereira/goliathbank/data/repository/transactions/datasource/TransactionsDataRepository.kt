package felipe.pereira.goliathbank.data.repository.transactions.datasource

import felipe.pereira.goliathbank.data.common.ResultWrapper
import felipe.pereira.goliathbank.data.common.getSafeResult
import felipe.pereira.goliathbank.data.repository.transactions.datasource.local.TransactionLocalDataSource
import felipe.pereira.goliathbank.data.repository.transactions.datasource.remote.TransactionsRemoteDataSource
import felipe.pereira.goliathbank.domain.transactions.TransactionsRepository
import felipe.pereira.goliathbank.domain.transactions.model.Transaction
import felipe.pereira.goliathbank.domain.transactions.usecase.GetTransactionsByCodeParams

class TransactionsDataRepository(
  private val remoteDataSource: TransactionsRemoteDataSource,
  private val localDataSource: TransactionLocalDataSource
) : TransactionsRepository {

  override suspend fun getTransactions(): ResultWrapper<List<Transaction>> =
    when (val result = remoteDataSource.getTransactions()) {
      is ResultWrapper.Success ->
        when (getSafeResult { localDataSource.saveTransactions(result.data) }) {
          is ResultWrapper.Success -> ResultWrapper.Success(result.data)
          is ResultWrapper.Error -> result
        }
      is ResultWrapper.Error -> result
    }

  override suspend fun getTransactionsByCode(params: GetTransactionsByCodeParams): ResultWrapper<List<Transaction>> =
    localDataSource.getTransactionByProduct(params.code)
}
