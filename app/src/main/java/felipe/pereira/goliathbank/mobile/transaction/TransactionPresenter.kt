package felipe.pereira.goliathbank.mobile.transaction

import felipe.pereira.goliathbank.data.common.ResultWrapper
import felipe.pereira.goliathbank.domain.currencyrates.usecase.GetEURCurrencyRates
import felipe.pereira.goliathbank.domain.transactions.usecase.GetTransactionsByCode
import felipe.pereira.goliathbank.domain.transactions.usecase.GetTransactionsByCodeParams
import felipe.pereira.goliathbank.mobile.common.Presenter
import felipe.pereira.goliathbank.mobile.transaction.model.TransactionViewEntity
import kotlinx.coroutines.launch

class TransactionPresenter(
  private val code: String,
  private val getTransactions: GetTransactionsByCode,
  private val getEURCurrencyRates: GetEURCurrencyRates
): Presenter<TransactionPresenter.TransactionView>() {

  override fun onViewAttached() {
    getView().initRecyclerView()
    launch {
      getCurrencyRates()
      getTransactions()
    }
  }

  private suspend fun getTransactions() {
    val params = GetTransactionsByCodeParams(code)
    when(getTransactions.buildAsync(params)) {
      is ResultWrapper.Success -> {

      }
      is ResultWrapper.Error -> {

      }
    }
  }

  private suspend fun getCurrencyRates() {
    when(val result = getEURCurrencyRates.buildAsync(Unit)) {
      is ResultWrapper.Success -> {

      }
      is ResultWrapper.Error -> {

      }
    }
  }

  interface TransactionView: View {
    fun initRecyclerView()
    fun showTransactions(transactions: List<TransactionViewEntity>)
  }
}