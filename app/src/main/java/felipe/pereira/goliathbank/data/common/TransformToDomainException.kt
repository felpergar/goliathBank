package felipe.pereira.goliathbank.data.common

internal class TransformToDomainException(private val entityName: String) : Exception() {

  override val message: String
    get() = "It was not possible transfrom $entityName to domain"
}