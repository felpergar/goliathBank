package felipe.pereira.goliathbank.mobile.transaction

import android.widget.Toast
import felipe.pereira.goliathbank.data.common.ResultWrapper
import felipe.pereira.goliathbank.domain.currencyrates.usecase.GetEURCurrencyRates
import felipe.pereira.goliathbank.domain.transactions.model.Transaction
import felipe.pereira.goliathbank.domain.transactions.usecase.GetTransactionsByCode
import felipe.pereira.goliathbank.domain.transactions.usecase.GetTransactionsByCodeParams
import felipe.pereira.goliathbank.mobile.common.Presenter
import felipe.pereira.goliathbank.mobile.transaction.model.TransactionViewEntity
import felipe.pereira.goliathbank.mobile.transaction.model.transformToTransactionQuantityUI
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TransactionPresenter(
  private val code: String,
  private val getTransactions: GetTransactionsByCode,
  private val getEURCurrencyRates: GetEURCurrencyRates
) : Presenter<TransactionPresenter.TransactionView>() {

  override fun onViewAttached() {
    getView().initRecyclerView()
    launch {
      getTransactions()
    }
  }

  private suspend fun getTransactions() {
    val params = GetTransactionsByCodeParams(code)
    when (val result = getTransactions.buildAsync(params)) {
      is ResultWrapper.Success -> {
        withContext(Main) { getView().showTransactions(result.data.transformToTransactionQuantityUI()) }
        getCurrencyRates(result.data)
      }
      is ResultWrapper.Error -> {
        withContext(Main) { getView().showError() }
      }
    }
  }

  private suspend fun getCurrencyRates(transactions: List<Transaction>) {
    when (val result = getEURCurrencyRates.buildAsync(Unit)) {
      is ResultWrapper.Success -> {
        val currencyRates = result.data
        var amount = 0F
        transactions.forEach { transaction ->
          val currencyRate = currencyRates.find { it.currencyFrom == transaction.currency }
          if (currencyRate != null)
            amount += currencyRate.rate * transaction.amount.toFloat()
        }
        withContext(Main) { getView().showAllAmount(amount.toString()) }

      }
      is ResultWrapper.Error -> {
        withContext(Main) { getView().showError() }
      }
    }
  }

  interface TransactionView : View {
    fun initRecyclerView()
    fun showTransactions(transactions: List<TransactionViewEntity>)
    fun showAllAmount(amount: String)
    fun showError()
  }
}