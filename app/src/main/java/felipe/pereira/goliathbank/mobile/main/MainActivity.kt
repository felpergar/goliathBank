package felipe.pereira.goliathbank.mobile.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import felipe.pereira.goliathbank.R
import felipe.pereira.goliathbank.databinding.ActivityMainBinding
import felipe.pereira.goliathbank.mobile.main.adapter.TransactionAdapter
import felipe.pereira.goliathbank.mobile.main.model.TransactionViewEntity
import org.koin.java.KoinJavaComponent

class MainActivity : AppCompatActivity(), MainPresenter.MainView {

  private val presenter: MainPresenter by KoinJavaComponent.inject(
    MainPresenter::class.java
  )
  private lateinit var binding: ActivityMainBinding
  private val transactionAdapter by lazy { TransactionAdapter() }

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

  override fun showTransactions(transactions: List<TransactionViewEntity>) {
    transactionAdapter.setItems(transactions)
  }

  override fun showLoading() {

  }

  override fun hideLoading() {
  }
}