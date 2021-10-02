package felipe.pereira.goliathbank.mobile.main

import android.widget.Toast
import felipe.pereira.goliathbank.data.common.ResultWrapper
import felipe.pereira.goliathbank.domain.currencyrates.usecase.GetCurrencyRates
import felipe.pereira.goliathbank.domain.transactions.usecase.GetTransactions
import felipe.pereira.goliathbank.mobile.common.Presenter
import felipe.pereira.goliathbank.mobile.main.model.TransactionCodeViewEntity
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPresenter(
  private val getCurrencyRates: GetCurrencyRates,
  private val getTransactions: GetTransactions
) : Presenter<MainPresenter.MainView>() {

  override fun onViewAttached() {
    getView().initRecyclerView()
    getView().showLoading()
    launch {
      getCurrencyRates()
      getTrans()
    }
  }

  private suspend fun getCurrencyRates() {
    when (val result = getCurrencyRates.buildAsync(Unit)) {
      is ResultWrapper.Success -> {
        println("Result ${result.data}")
      }
      is ResultWrapper.Error -> {
        //TODO show error
        println("Result ${result.throwable.message}")
      }
    }
    withContext(Main) { getView().hideLoading() }
  }

  private suspend fun getTrans() {
    when (val result = getTransactions.buildAsync(Unit)) {
      is ResultWrapper.Success -> {
        val result = result.data.groupBy { it.code }

        val transactions = result.map { TransactionCodeViewEntity(it.key) }
        withContext(Main) { getView().showTransactions(transactions) }
      }
      is ResultWrapper.Error -> {
        println("Result ${result.throwable.message}")
      }
    }
  }

  fun onTransactionClicked(code: String) {
    getView().navigateToTransactionScreen(code)
  }

  interface MainView : View {
    fun initRecyclerView()
    fun showTransactions(transactions: List<TransactionCodeViewEntity>)
    fun navigateToTransactionScreen(code: String)
    fun showError()
  }
}