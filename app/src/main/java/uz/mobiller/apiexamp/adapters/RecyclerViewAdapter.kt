package uz.mobiller.apiexamp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.mobiller.apiexamp.databinding.ItemCurrencyBinding
import uz.mobiller.apiexamp.model.JsonObjectItem

class RecyclerViewAdapter(
    var list: List<JsonObjectItem>,
    var listener: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(var binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(itemModel: JsonObjectItem, position: Int) {
            binding.tvTitle.text = itemModel.title
            binding.code.text = itemModel.code
            binding.cbPrice.text = itemModel.cb_price
            binding.date.text = itemModel.date
            binding.root.setOnClickListener {
                listener.onItemClick(itemModel, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemCurrencyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currencyModelItem = list[position]
        holder.onBind(currencyModelItem, position)
    }

    interface OnItemClickListener {
        fun onItemClick(itemModel: JsonObjectItem, position: Int)
    }
}