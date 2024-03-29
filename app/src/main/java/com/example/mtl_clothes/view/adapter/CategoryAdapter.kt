package com.example.mtl_clothes.view.adapter

import android.content.ClipData.Item
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mtl_clothes.databinding.ItemCategoriesBinding

class CategoryAdapter(var mContext: Context, var callBack: ICallBack) :
    RecyclerView.Adapter<CategoryAdapter.ItemViewHolder>() {
    open class ItemViewHolder(val binding: ItemCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root)

    var listCategories: MutableList<String> = mutableListOf()
    var pos = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemCategoriesBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return ItemViewHolder(binding)
    }

    interface ICallBack {
        fun callBack(position: Int,categories: String)
    }

    fun updateData(newData: MutableList<String>) {
        listCategories.clear()
        listCategories.addAll(newData)
        notifyDataSetChanged()
    }

    fun updatePosition(newPosition: Int) {
        pos = newPosition
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listCategories.size

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var category = listCategories[position]
        holder.binding.tvName.text = category
        if(pos ==position){
            holder.binding.tvName.setTextColor(Color.parseColor("#6342E8"))
            holder.binding.ivHr.visibility = View.VISIBLE
        }else{
            holder.binding.tvName.setTextColor(Color.parseColor("#A1A1A1"))
            holder.binding.ivHr.visibility = View.GONE
        }
        holder.binding.root.setOnClickListener {
            callBack.callBack(position,category)
        }
    }

}