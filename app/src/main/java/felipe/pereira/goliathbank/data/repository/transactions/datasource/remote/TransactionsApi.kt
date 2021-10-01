package felipe.pereira.goliathbank.data.repository.transactions.datasource.remote

import felipe.pereira.goliathbank.data.repository.transactions.datasource.remote.model.TransactionRemoteEntity
import retrofit2.http.GET

interface TransactionsApi {

  @GET("/transactions.json")
  suspend fun getTransactions(): List<TransactionRemoteEntity>
}