package felipe.pereira.goliathbank.data.repository.transactions.datasource

import felipe.pereira.goliathbank.data.common.ResultWrapper
import felipe.pereira.goliathbank.data.repository.transactions.datasource.remote.TransactionsRemoteDataSource
import felipe.pereira.goliathbank.domain.transactions.TransactionsRepository
import felipe.pereira.goliathbank.domain.transactions.model.Transactions

class TransactionsDataRepository(
  private val remoteDataSource: TransactionsRemoteDataSource
): TransactionsRepository {

  override suspend fun getTransactions(): ResultWrapper<List<Transactions>> = remoteDataSource.getTransactions()
}