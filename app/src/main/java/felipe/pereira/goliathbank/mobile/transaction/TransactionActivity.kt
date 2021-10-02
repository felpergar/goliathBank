package felipe.pereira.goliathbank.mobile.transaction

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import felipe.pereira.goliathbank.R
import felipe.pereira.goliathbank.databinding.ActivityTransactionBinding
import felipe.pereira.goliathbank.mobile.transaction.model.TransactionViewEntity
import felipe.pereira.goliathbank.mobile.transaction.adapter.TransactionQuantityAdapter
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent

class TransactionActivity : AppCompatActivity(), TransactionPresenter.TransactionView {

  private lateinit var binding: ActivityTransactionBinding
  private val code by lazy { intent?.getStringExtra(CODE) ?: "" }

  private val presenter: TransactionPresenter by KoinJavaComponent.inject(TransactionPresenter::class.java) { parametersOf(code) }
  private val transactionAdapter by lazy { TransactionQuantityAdapter() }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityTransactionBinding.inflate(layoutInflater)
    setContentView(binding.root)
    presenter.attachView(this)
  }

  override fun initRecyclerView() {
    with(binding.transactionRecyclerView) {
      layoutManager = LinearLayoutManager(this@TransactionActivity)
      setHasFixedSize(true)
      if (adapter == null)
        adapter = transactionAdapter
    }
  }

  override fun showTransactions(transactions: List<TransactionViewEntity>) {
    transactionAdapter.setItems(transactions)
  }

  override fun showLoading() {
    TODO("Not yet implemented")
  }

  override fun hideLoading() {
    TODO("Not yet implemented")
  }

  companion object {
    private const val CODE = "code"

    fun newCallingIntent(context: Context, code: String) = Intent(context, TransactionActivity::class.java).apply {
      putExtra(CODE, code)
    }
  }
}