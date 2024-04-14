package com.example.mtl_clothes.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mtl_clothes.R
import com.example.mtl_clothes.base_interface.IProduct
import com.example.mtl_clothes.databinding.ItemProductBinding
import com.example.mtl_clothes.api.res.ProductRes
import com.example.mtl_clothes.ultis.ConvertCurrency

class ProductAdapter(var mContext: Context, var callback: IProduct) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    var listProduct: MutableList<ProductRes> = mutableListOf()


    class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    fun updateDateByPosition(position: Int) {
        if (position >= 0 && position < listProduct.size) {
            notifyItemChanged(position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: MutableList<ProductRes>) {
        listProduct.clear()
        listProduct.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        var product = listProduct[position]

        holder.binding.tvPrices.text =
            ConvertCurrency.getInstance().convertToUSD(product.price.toFloat())
        holder.binding.tvName.text = product.name

        if (product.photos.isNotEmpty()) {
            Glide
                .with(mContext)
                .load(product.photos[0])
                .centerCrop()
                .into(holder.binding.ivClothes)
        } else {
            Glide
                .with(mContext)
                .load(R.drawable.img_clothes)
                .centerCrop()
                .into(holder.binding.ivClothes)
        }


        holder.binding.root.setOnClickListener {
            callback.callBackProduct(product)
        }
    }


}