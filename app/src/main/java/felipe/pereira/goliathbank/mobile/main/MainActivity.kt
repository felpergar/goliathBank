package felipe.pereira.goliathbank.mobile.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import felipe.pereira.goliathbank.R
import org.koin.java.KoinJavaComponent

class MainActivity : AppCompatActivity(), MainPresenter.MainView {

  private val presenter: MainPresenter by KoinJavaComponent.inject(
    MainPresenter::class.java
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    presenter.attachView(this)
  }

  override fun showLoading() {
  }

  override fun hideLoading() {
  }
}