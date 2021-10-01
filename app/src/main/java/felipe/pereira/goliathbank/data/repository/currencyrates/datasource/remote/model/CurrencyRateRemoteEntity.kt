package felipe.pereira.goliathbank.data.repository.currencyrates.datasource.remote.model

import com.google.gson.annotations.SerializedName
import felipe.pereira.goliathbank.data.common.TransformToDomainException
import felipe.pereira.goliathbank.domain.currencyrates.model.CurrencyRate

class CurrencyRateRemoteEntity(
  @SerializedName(FROM) val currencyFrom: String?,
  @SerializedName(TO) val currencyTo: String?,
  @SerializedName(RATE) val rate: Float?
) {

  fun isValid() = !currencyFrom.isNullOrBlank() && !currencyTo.isNullOrBlank() && rate != null
}

private fun CurrencyRateRemoteEntity.transformToDomain() = CurrencyRate(currencyFrom!!, currencyTo!!, rate!!)

fun List<CurrencyRateRemoteEntity>.transformToDomain(): List<CurrencyRate> =
  map { if (it.isValid()) it.transformToDomain() else throw TransformToDomainException(this.javaClass.name) }

const val FROM = "from"
private const val TO = "to"
private const val RATE = "rate"