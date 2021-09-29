package felipe.pereira.goliathbank.data.repository.transactions.datasource.remote

import felipe.pereira.goliathbank.data.common.getSafeResult
import felipe.pereira.goliathbank.data.repository.transactions.datasource.remote.model.transformToDomain
import retrofit2.Retrofit

class TransactionsRemoteDataSource(
  retrofit: Retrofit,
  private val api: TransactionsApi = retrofit.create(TransactionsApi::class.java)
) {

  suspend fun getTransactions() = getSafeResult { api.getTransactions().transformToDomain() }
}