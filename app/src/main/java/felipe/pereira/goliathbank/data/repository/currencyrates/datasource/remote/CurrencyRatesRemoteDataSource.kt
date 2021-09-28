package felipe.pereira.goliathbank.data.repository.currencyrates.datasource.remote

import felipe.pereira.goliathbank.data.common.ResultWrapper
import felipe.pereira.goliathbank.data.common.getSafeResult
import felipe.pereira.goliathbank.data.repository.currencyrates.datasource.remote.model.transformToDomain
import felipe.pereira.goliathbank.domain.currencyrates.model.CurrencyRate
import retrofit2.Retrofit

class CurrencyRatesRemoteDataSource(
  retrofit: Retrofit,
  private val api: CurrencyRatesApi = retrofit.create(CurrencyRatesApi::class.java)
) {

  suspend fun getCurrencyRates(): ResultWrapper<List<CurrencyRate>> = getSafeResult {
    api.getCurrencyRates().transformToDomain()
  }
}