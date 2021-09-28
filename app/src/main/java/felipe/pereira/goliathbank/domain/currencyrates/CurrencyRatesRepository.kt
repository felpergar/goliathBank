package felipe.pereira.goliathbank.domain.currencyrates

import felipe.pereira.goliathbank.data.common.ResultWrapper
import felipe.pereira.goliathbank.domain.currencyrates.model.CurrencyRate

interface CurrencyRatesRepository {

  suspend fun getCurrencyRates(): ResultWrapper<List<CurrencyRate>>
}