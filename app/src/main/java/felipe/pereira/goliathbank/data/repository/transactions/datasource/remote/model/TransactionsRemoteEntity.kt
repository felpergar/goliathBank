package felipe.pereira.goliathbank.data.repository.transactions.datasource.remote.model

import com.google.gson.annotations.SerializedName
import felipe.pereira.goliathbank.data.common.TransformToDomainException
import felipe.pereira.goliathbank.domain.transactions.model.Transactions

class TransactionsRemoteEntity(
  @SerializedName(SKU) val code: String?,
  @SerializedName(AMOUNT) val amount: String?,
  @SerializedName(CURRENCY) val currency: String?
) {

  fun isValid() = !code.isNullOrBlank() && !amount.isNullOrBlank() && !currency.isNullOrBlank()
}

private fun TransactionsRemoteEntity.transformToDomain() = Transactions(code!!, amount!!, currency!!)

fun List<TransactionsRemoteEntity>.transformToDomain() =
  map { if (it.isValid()) it.transformToDomain() else throw TransformToDomainException(this::javaClass.name) }

private const val SKU = "sku"
private const val AMOUNT = "amount"
private const val CURRENCY = "currency"