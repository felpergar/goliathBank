package felipe.pereira.goliathbank.mobile.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import felipe.pereira.goliathbank.databinding.ActivityMainBinding
import felipe.pereira.goliathbank.mobile.main.adapter.TransactionAdapter
import felipe.pereira.goliathbank.mobile.main.model.TransactionCodeViewEntity
import felipe.pereira.goliathbank.mobile.transaction.TransactionActivity
import org.koin.java.KoinJavaComponent

class MainActivity : AppCompatActivity(), MainPresenter.MainView {

  private val presenter: MainPresenter by KoinJavaComponent.inject(
    MainPresenter::class.java
  )
  private lateinit var binding: ActivityMainBinding
  private val transactionAdapter by lazy { TransactionAdapter(presenter::onTransactionClicked) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    presenter.attachView(this)
  }

  override fun initRecyclerView() {
    with(binding.mainRecyclerView) {
      layoutManager = LinearLayoutManager(context!!)
      setHasFixedSize(true)
      if (adapter == null)
        adapter = transactionAdapter
    }
  }

  override fun showTransactions(transactions: List<TransactionCodeViewEntity>) {
    transactionAdapter.setItems(transactions)
  }

  override fun navigateToTransactionScreen(code: String) {
    startActivity(TransactionActivity.newCallingIntent(this, code))
  }

  override fun showError() {
    Toast.makeText(this, "text", Toast.LENGTH_SHORT).show()
  }


  override fun showLoading() {

  }

  override fun hideLoading() {

  }
}