package felipe.pereira.goliathbank.data.common

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit = Retrofit.Builder()
  .baseUrl("https://quiet-stone-2094.herokuapp.com")
  .addConverterFactory(GsonConverterFactory.create())
  .build()