package com.example.mtl_clothes.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.example.mtl_clothes.R
import com.example.mtl_clothes.base_interface.IOrder
import com.example.mtl_clothes.base_interface.IProduct
import com.example.mtl_clothes.databinding.ItemOrderBinding
import com.example.mtl_clothes.databinding.ItemProductBinding
import com.example.mtl_clothes.model.ProductModel
import com.example.mtl_clothes.ultis.ConvertCurrency

class OrderAdapter(var mContext: Context, var callback: IOrder) :
    RecyclerView.Adapter<OrderAdapter.ItemViewHolder>() {
    var listProduct: MutableList<ProductModel> = mutableListOf()


    class ItemViewHolder(val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    fun updateDateByPosition(position: Int,isFavorites:Boolean) {
        if (position >= 0 && position < listProduct.size) {
            val product = listProduct[position]
            product.isFavorites = isFavorites
            notifyItemChanged(position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: MutableList<ProductModel>) {
        listProduct.clear()
        listProduct.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var product = listProduct[position]

        holder.binding.tvPrices.text =
            ConvertCurrency.getInstance().convertToUSD(product.prices.trim().toFloat())
        holder.binding.tvName.text = product.name
        Glide
            .with(mContext)
            .load(product.imageLink)
            .centerCrop()
            .into(holder.binding.ivClothes)
        holder.binding.ivDelete.setOnClickListener{
            callback.callbackDelete(position)

        }
        holder.binding.root.setOnClickListener {
            callback.callBackOrder(product)
        }
    }


}