package felipe.pereira.goliathbank.mobile.transaction.model

import felipe.pereira.goliathbank.domain.transactions.model.Transaction

class TransactionViewEntity(
  val code: String,
  val amount: String,
  val currency: String
)

private fun Transaction.transformToTransactionQuantityUI() = TransactionViewEntity(code, amount, code)

fun List<Transaction>.transformToTransactionQuantityUI() = map { it.transformToTransactionQuantityUI() }