package com.example.mtl_clothes.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mtl_clothes.R
import com.example.mtl_clothes.api.res.OrderSub
import com.example.mtl_clothes.api.res.ProductRes
import com.example.mtl_clothes.base_interface.IOrder
import com.example.mtl_clothes.databinding.ItemOrderBinding
import com.example.mtl_clothes.database.model.OrderModel
import com.example.mtl_clothes.databinding.ItemOrderTrackBinding
import com.example.mtl_clothes.ultis.ConvertCurrency

class OrderTrackAdapter(var mContext: Context) :
    RecyclerView.Adapter<OrderTrackAdapter.ItemViewHolder>() {
    var listOrderSub: ArrayList<OrderSub> = arrayListOf()

    class ItemViewHolder(val binding: ItemOrderTrackBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemOrderTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    fun updateDateByPosition(position: Int,isFavorites:Boolean) {
        if (position >= 0 && position < listOrderSub.size) {
            val product = listOrderSub[position]
            notifyItemChanged(position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: ArrayList<OrderSub>) {
        listOrderSub.clear()
        listOrderSub.addAll(newList)
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateProduct(newList: ArrayList<OrderSub>) {
        listOrderSub.clear()
        listOrderSub.addAll(newList)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return listOrderSub.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var order = listOrderSub[position]

        holder.binding.tvStatus.text = order.status
        holder.binding.tvName.text = order.products?.get(0)?.productName ?: "Áo"
        holder.binding.tvQuantity.text = "x${order.products?.get(0)?.quantity?:"1"}"
        holder.binding.tvTotalPrices.text = ConvertCurrency.getInstance().convertToUSD(order.totalPrice.toFloat())
        holder.binding.tvAddress.text = order.shippingAddress
        try {
            Glide.with(mContext)
                .load(order.products?.get(0)?.listPhoto?.get(0))
                .into(holder.binding.imgPicture)
        }catch (e:Exception){
            Glide.with(mContext)
                .load(R.drawable.img_clothes)
                .into(holder.binding.imgPicture)
        }

    }


}