package felipe.pereira.goliathbank.data.repository.currencyrates.datasource.local

import felipe.pereira.goliathbank.data.common.getSafeResult
import felipe.pereira.goliathbank.data.repository.currencyrates.datasource.local.model.transformToDomain
import felipe.pereira.goliathbank.data.repository.currencyrates.datasource.local.model.transformToLocalEntity
import felipe.pereira.goliathbank.domain.currencyrates.model.CurrencyRate

class CurrencyRatesLocalDataSource(
  private val dao: CurrencyRateDao
) {

  suspend fun getCurrencyRates() = getSafeResult { dao.getCurrencyRates().transformToDomain() }

  suspend fun saveCurrencyRates(rates: List<CurrencyRate>) = getSafeResult {
//    dao.deleteDataBase()
    dao.saveCurrencyRates(rates.transformToLocalEntity())
  }
}