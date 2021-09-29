package felipe.pereira.goliathbank.mobile.main

import felipe.pereira.goliathbank.data.common.ResultWrapper
import felipe.pereira.goliathbank.domain.currencyrates.usecase.GetCurrencyRates
import felipe.pereira.goliathbank.domain.transactions.usecase.GetTransactions
import felipe.pereira.goliathbank.mobile.common.Presenter
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPresenter(
  private val getCurrencyRates: GetCurrencyRates,
  private val getTransactions: GetTransactions
) : Presenter<MainPresenter.MainView>() {

  override fun onViewAttached() {
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
        println("Result ${result.throwable.message}")
      }
    }
    withContext(Main) { getView().hideLoading() }
  }

  private suspend fun getTrans() {
    when (val result = getTransactions.buildAsync(Unit)) {
      is ResultWrapper.Success -> {
        println("Result ${result.data}")
      }
      is ResultWrapper.Error -> {
        println("Result ${result.throwable.message}")
      }
    }
  }

  interface MainView : View
}