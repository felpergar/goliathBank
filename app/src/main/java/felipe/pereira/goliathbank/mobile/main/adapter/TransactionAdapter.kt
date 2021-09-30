package felipe.pereira.goliathbank.mobile.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import felipe.pereira.goliathbank.R
import felipe.pereira.goliathbank.databinding.AdapterTransactionBinding
import felipe.pereira.goliathbank.mobile.main.model.TransactionViewEntity

class TransactionAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  var items: ArrayList<TransactionViewEntity> = arrayListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
    TransactionViewHolder(
      LayoutInflater.from(parent.context).inflate(R.layout.adapter_transaction, parent, false)
    )

  override fun getItemCount(): Int = items.size

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
    (holder as TransactionViewHolder).bind(items[position])

  fun setItems(transactions: List<TransactionViewEntity>) {
    items.addAll(transactions)
    notifyDataSetChanged()
  }

  inner class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: TransactionViewEntity) {
      with(AdapterTransactionBinding.bind(itemView)) {
        code.text = item.code
      }
    }
  }
}