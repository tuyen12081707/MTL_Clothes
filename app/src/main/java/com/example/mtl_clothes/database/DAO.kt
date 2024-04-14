package com.example.mtl_clothes.database

import androidx.room.*
import com.example.mtl_clothes.database.model.OrderModel


@Dao
interface DAO {

    //    @Query("SELECT IfNULL(SUM(value),0) as value FROM drink WHERE date LIKE '%'||:time||'%'")
//    fun getAllMl(time : String) : Int
    @Insert
    fun insertOrder(vararg order: OrderModel)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(order: OrderModel)
    @Update
    fun updateOrder(vararg order: OrderModel)
    @Query("SELECT * FROM order_table ORDER BY id DESC")
    fun getAllOrder(): List<OrderModel>
    @Delete
    fun deleteOrder(order : OrderModel)
    @Query("SELECT * FROM order_table WHERE id = :productId LIMIT 1")
    fun getOrderById(productId: String): OrderModel?
}