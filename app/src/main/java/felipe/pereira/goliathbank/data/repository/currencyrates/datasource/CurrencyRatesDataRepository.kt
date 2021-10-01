package felipe.pereira.goliathbank.data.repository.currencyrates.datasource

import felipe.pereira.goliathbank.data.common.ResultWrapper
import felipe.pereira.goliathbank.data.repository.currencyrates.datasource.remote.CurrencyRatesRemoteDataSource
import felipe.pereira.goliathbank.domain.currencyrates.CurrencyRatesRepository
import felipe.pereira.goliathbank.domain.currencyrates.model.CurrencyRate

class CurrencyRatesDataRepository(
  private val remoteDataSource: CurrencyRatesRemoteDataSource
) : CurrencyRatesRepository {

  override suspend fun getCurrencyRates(): ResultWrapper<List<CurrencyRate>> {
    when (val result = remoteDataSource.getCurrencyRates()) {
      is ResultWrapper.Success -> {
        val rates = result.data.groupBy { it.currencyTo }
        val allEurRates = mutableListOf<CurrencyRate>()

        val currencies = rates.map { it.key }
        val eurRates = rates[EUR]
        eurRates?.let { allEurRates.addAll(it.toList()) }

        while (allEurRates.size < currencies.size) {
          val newRates = mutableListOf<CurrencyRate>()
          allEurRates.forEach { eurRate ->
            rates[eurRate.currencyFrom]?.forEach { rateWithNewCurrency ->
              if (allEurRates.find { it.currencyFrom == rateWithNewCurrency.currencyFrom } == null)
                newRates.add(CurrencyRate(rateWithNewCurrency.currencyFrom, EUR, eurRate.rate * rateWithNewCurrency.rate))
            }
          }

          allEurRates.addAll(newRates)
        } //I suppossed all types of rates can be calculated ?!
        return result
      }
      is ResultWrapper.Error -> {
        return result
      }
    }
  }
}

private const val EUR = "EUR"