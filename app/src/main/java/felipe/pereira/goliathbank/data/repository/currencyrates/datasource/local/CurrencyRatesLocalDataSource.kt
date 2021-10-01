package felipe.pereira.goliathbank.data.repository.currencyrates.datasource.local

import felipe.pereira.goliathbank.data.common.getSafeResult
import felipe.pereira.goliathbank.data.repository.currencyrates.datasource.local.model.transformToDomain
import felipe.pereira.goliathbank.data.repository.currencyrates.datasource.local.model.transformToLocalEntity
import felipe.pereira.goliathbank.domain.currencyrates.model.CurrencyRate

class CurrencyRatesLocalDataSource(
  private val dao: CurrencyRateDao
) {

  suspend fun getRates() = getSafeResult { dao.getRates().transformToDomain() }

  suspend fun saveRates(rates: List<CurrencyRate>) = getSafeResult { dao.saveRates(rates.transformToLocalEntity()) }
}