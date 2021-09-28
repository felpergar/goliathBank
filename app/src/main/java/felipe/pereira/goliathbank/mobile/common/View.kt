package felipe.pereira.goliathbank.mobile.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class Presenter<V : Presenter.View> : CoroutineScope {

  private val job = SupervisorJob()
  override val coroutineContext: CoroutineContext
    get() = Dispatchers.IO + job

  private var view: V? = null

  fun attachView(view: V) {
    this.view = view
    onViewAttached()
  }

  protected abstract fun onViewAttached()

  open fun detachView() {
    job.cancel()
    this.view = null
  }

  fun getView(): V = view!!

  interface View {
    fun showLoading()
    fun hideLoading()
  }
}
