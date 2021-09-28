package felipe.pereira.goliathbank.data.repository.currencyrates.datasource.remote

import felipe.pereira.goliathbank.data.repository.currencyrates.datasource.remote.model.CurrencyRateRemoteEntity
import retrofit2.http.GET

interface CurrencyRatesApi {

 @GET("/rates.json")
 suspend fun getCurrencyRates(): List<CurrencyRateRemoteEntity>
}