package felipe.pereira.goliathbank.di

import felipe.pereira.goliathbank.data.common.retrofit
import felipe.pereira.goliathbank.data.database.GoliathDataBase
import felipe.pereira.goliathbank.data.repository.currencyrates.datasource.CurrencyRatesDataRepository
import felipe.pereira.goliathbank.data.repository.currencyrates.datasource.local.CurrencyRatesLocalDataSource
import felipe.pereira.goliathbank.data.repository.currencyrates.datasource.remote.CurrencyRatesRemoteDataSource
import felipe.pereira.goliathbank.data.repository.transactions.datasource.TransactionsDataRepository
import felipe.pereira.goliathbank.data.repository.transactions.datasource.local.TransactionDao
import felipe.pereira.goliathbank.data.repository.transactions.datasource.local.TransactionLocalDataSource
import felipe.pereira.goliathbank.data.repository.transactions.datasource.remote.TransactionsRemoteDataSource
import felipe.pereira.goliathbank.domain.currencyrates.CurrencyRatesRepository
import felipe.pereira.goliathbank.domain.currencyrates.usecase.GetCurrencyRates
import felipe.pereira.goliathbank.domain.transactions.TransactionsRepository
import felipe.pereira.goliathbank.domain.transactions.usecase.GetTransactions
import felipe.pereira.goliathbank.mobile.main.MainPresenter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

  single { CurrencyRatesRemoteDataSource(retrofit) }
  single { TransactionsRemoteDataSource(retrofit) }
  single { GoliathDataBase.buildDatabase(androidContext()).getTransactionDataBase() }
  single { GoliathDataBase.buildDatabase(androidContext()).getCurrencyRateDataBase() }

  single { TransactionLocalDataSource(get()) }
  single { CurrencyRatesLocalDataSource(get()) }
  single<CurrencyRatesRepository> { CurrencyRatesDataRepository(get(), get()) }
  single<TransactionsRepository> { TransactionsDataRepository(get(), get()) }

  factory { GetCurrencyRates(get()) }
  factory { GetTransactions(get()) }
  factory { MainPresenter(get(), get()) }
}
