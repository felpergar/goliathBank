package felipe.pereira.goliathbank.domain.currencyrates.usecase

import felipe.pereira.goliathbank.data.common.ResultWrapper
import felipe.pereira.goliathbank.domain.UseCaseAsync
import felipe.pereira.goliathbank.domain.currencyrates.CurrencyRatesRepository
import felipe.pereira.goliathbank.domain.currencyrates.model.CurrencyRate

class GetCurrencyRates(
  private val repository: CurrencyRatesRepository
): UseCaseAsync<Unit, List<CurrencyRate>> {

  override suspend fun buildAsync(params: Unit): ResultWrapper<List<CurrencyRate>> = repository.getCurrencyRates()
}