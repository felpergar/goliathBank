package felipe.pereira.goliathbank.mobile.main.model

import felipe.pereira.goliathbank.domain.transactions.model.Transaction

class TransactionViewEntity(
  val code: String
)

private fun Transaction.transformToUI() = TransactionViewEntity(code)

fun List<Transaction>.transformToUI() = map { it.transformToUI() }