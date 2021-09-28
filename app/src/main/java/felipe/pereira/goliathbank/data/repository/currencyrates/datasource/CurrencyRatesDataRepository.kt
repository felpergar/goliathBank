package felipe.pereira.goliathbank.data.repository.currencyrates.datasource

import felipe.pereira.goliathbank.data.common.ResultWrapper
import felipe.pereira.goliathbank.data.repository.currencyrates.datasource.remote.CurrencyRatesRemoteDataSource
import felipe.pereira.goliathbank.domain.currencyrates.CurrencyRatesRepository
import felipe.pereira.goliathbank.domain.currencyrates.model.CurrencyRate

class CurrencyRatesDataRepository(
  private val remoteDataSource: CurrencyRatesRemoteDataSource
): CurrencyRatesRepository {

  override suspend fun getCurrencyRates(): ResultWrapper<List<CurrencyRate>> = remoteDataSource.getCurrencyRates()
}