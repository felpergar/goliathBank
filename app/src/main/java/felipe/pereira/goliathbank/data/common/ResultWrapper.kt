package felipe.pereira.goliathbank.data.common

sealed class ResultWrapper<out T> {
  data class Success<out T>(val data: T) : ResultWrapper<T>()
  data class Error(val throwable: Throwable) : ResultWrapper<Nothing>()
}

suspend fun <T> getSafeResult(call: suspend () -> T): ResultWrapper<T> = try {
  ResultWrapper.Success(call())
} catch (e: Exception) {
  ResultWrapper.Error(e)
}