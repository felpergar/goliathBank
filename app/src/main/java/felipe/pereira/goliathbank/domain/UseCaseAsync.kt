package felipe.pereira.goliathbank.domain

import felipe.pereira.goliathbank.data.common.ResultWrapper

interface UseCaseAsync<in RQ, RS> {

  suspend fun buildAsync(params: RQ): ResultWrapper<RS>
}