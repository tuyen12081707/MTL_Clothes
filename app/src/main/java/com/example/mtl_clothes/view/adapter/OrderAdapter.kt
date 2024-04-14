package com.example.mtl_clothes.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mtl_clothes.R
import com.example.mtl_clothes.base_interface.IOrder
import com.example.mtl_clothes.databinding.ItemOrderBinding
import com.example.mtl_clothes.database.model.OrderModel
import com.example.mtl_clothes.ultis.ConvertCurrency

class OrderAdapter(var mContext: Context, var callback: IOrder) :
    RecyclerView.Adapter<OrderAdapter.ItemViewHolder>() {
    var listProduct: ArrayList<OrderModel> = arrayListOf()


    class ItemViewHolder(val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    fun updateDateByPosition(position: Int,isFavorites:Boolean) {
        if (position >= 0 && position < listProduct.size) {
            val product = listProduct[position]
            notifyItemChanged(position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<OrderModel>) {
        listProduct.clear()
        listProduct.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var product = listProduct[position]
        var order = listProduct[position]
        holder.binding.tvNumber.text = order.quantity_order.toString()
        product.let {
            holder.binding.tvPrices.text =
                it?.price?.toFloat()?.let { it1 -> ConvertCurrency.getInstance().convertToUSD(it1) }
            holder.binding.tvName.text = it?.name
            if (it?.photos?.isNotEmpty() == true) {
                Glide
                    .with(mContext)
                    .load(it.photos!![0])
                    .centerCrop()
                    .into(holder.binding.ivClothes)
            } else {
                Glide
                    .with(mContext)
                    .load(R.drawable.img_clothes)
                    .centerCrop()
                    .into(holder.binding.ivClothes)
            }


        }
        holder.binding.ivDelete.setOnClickListener{
            callback.callbackDelete(listProduct[position])

        }
        holder.binding.root.setOnClickListener {
            callback.callBackOrder(listProduct[position])
        }

    }


}