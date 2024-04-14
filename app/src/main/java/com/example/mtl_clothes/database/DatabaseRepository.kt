package com.example.mtl_clothes.database

import android.content.Context
import com.example.mtl_clothes.database.callback.GetAllCallBack
import com.example.mtl_clothes.database.callback.InsertCallBack
import com.example.mtl_clothes.database.model.OrderModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DatabaseRepository {

    fun insertOrder(
        context: Context,
        order: OrderModel,
        orderCallBack: InsertCallBack
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val dao = APP_DATABASE.requestDatabase(context).dao()
            val existingOrder = dao.getOrderById(order.id)
            if (existingOrder != null) {
                dao.updateOrder(order)
            } else {
                dao.insertOrder(order)
            }
            withContext(Dispatchers.Main) {
                orderCallBack.onSucess()
            }
        }
    }

    fun getAllOrder(context: Context, getNoteCallBack: GetAllCallBack) {
        CoroutineScope(Dispatchers.IO).launch {
            val data = APP_DATABASE.requestDatabase(context).dao().getAllOrder()
            withContext(Dispatchers.Main){
                getNoteCallBack.onSucess(data)
            }
        }
    }
    fun deleteOrder(
        context: Context,
        order: OrderModel, orderCallBack: InsertCallBack
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            APP_DATABASE.requestDatabase(context).dao().deleteOrder(order)
            withContext(Dispatchers.Main) {
                orderCallBack.onSucess()
            }
        }
    }
}