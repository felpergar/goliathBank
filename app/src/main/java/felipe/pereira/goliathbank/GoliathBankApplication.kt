package felipe.pereira.goliathbank

import android.app.Application
import felipe.pereira.goliathbank.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GoliathBankApplication: Application() {

  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidContext(this@GoliathBankApplication)
      modules(appModule)
    }
  }
}