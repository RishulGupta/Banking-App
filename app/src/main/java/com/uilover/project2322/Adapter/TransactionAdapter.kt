package com.uilover.project2322.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uilover.project2322.Domain.Transction
import com.uilover.project2322.databinding.ViewholderLastTransactionBinding

class TransactionAdapter(private val list: ArrayList<Transction>) :
    RecyclerView.Adapter<TransactionAdapter.Viewholder>() {
    class Viewholder(val binding: ViewholderLastTransactionBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionAdapter.Viewholder {
        val binding = ViewholderLastTransactionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: TransactionAdapter.Viewholder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(list[position].imageUrl)
            .into(holder.binding.img)

        holder.binding.priceTxt.text = "$" + list[position].amount
        holder.binding.locationTxt.text = list[position].name
        holder.binding.dateTxt.text = list[position].date

        val amount = list[position].amount.toDouble()
        if(amount>=0){
            holder.binding.priceTxt.setTextColor(Color.GREEN)
        }else{
            holder.binding.priceTxt.setTextColor(Color.RED)
        }
    }

    override fun getItemCount(): Int = list.size

}