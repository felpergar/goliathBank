package felipe.pereira.goliathbank.di

import felipe.pereira.goliathbank.data.common.retrofit
import felipe.pereira.goliathbank.data.repository.currencyrates.datasource.CurrencyRatesDataRepository
import felipe.pereira.goliathbank.data.repository.currencyrates.datasource.remote.CurrencyRatesRemoteDataSource
import felipe.pereira.goliathbank.domain.currencyrates.CurrencyRatesRepository
import felipe.pereira.goliathbank.domain.currencyrates.usecase.GetCurrencyRates
import felipe.pereira.goliathbank.mobile.main.MainPresenter
import org.koin.dsl.module

val appModule = module {

  single { CurrencyRatesRemoteDataSource(retrofit) }
  single<CurrencyRatesRepository> { CurrencyRatesDataRepository(get()) }

  factory { GetCurrencyRates(get()) }
  factory { MainPresenter(get()) }
}
