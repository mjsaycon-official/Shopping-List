package com.example.shoppinglist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.ShoppingItem
import com.example.shoppinglist.viewModel.ShoppingViewModel

class ShoppingAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
): RecyclerView.Adapter<ShoppingAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val tvAmount = itemView.findViewById<TextView>(R.id.tvAmount)
        val icMinus = itemView.findViewById<ImageView>(R.id.icMinus)
        val icAdd = itemView.findViewById<ImageView>(R.id.icAdd)
        val icDelete = itemView.findViewById<ImageView>(R.id.icDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = items[position]
        holder.tvName.text = item.name
        holder.tvAmount.text = "${item.amount}"

        holder.icDelete.setOnClickListener{
            viewModel.delete(item)
        }

        holder.icAdd.setOnClickListener{
            item.amount++
            viewModel.upsert(item)
        }

        holder.icMinus.setOnClickListener{
            if (item.amount > 0) {
                item.amount--
                viewModel.upsert(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}