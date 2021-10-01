package felipe.pereira.goliathbank.data.repository.currencyrates.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import felipe.pereira.goliathbank.domain.currencyrates.model.CurrencyRate

@Entity(tableName = "rate")
class CurrencyRateLocalEntity(
  @PrimaryKey val id: String,
  val currencyFrom: String,
  val currencyTo: String,
  val rate: Float
)

private fun CurrencyRateLocalEntity.transformToDomain() = CurrencyRate(currencyFrom, currencyTo, rate)

private fun CurrencyRate.transformToLocalEntity() = CurrencyRateLocalEntity(System.currentTimeMillis().toString(), currencyFrom, currencyTo, rate)

fun List<CurrencyRateLocalEntity>.transformToDomain() = map { it.transformToDomain() }

fun List<CurrencyRate>.transformToLocalEntity() = map { it.transformToLocalEntity() }


