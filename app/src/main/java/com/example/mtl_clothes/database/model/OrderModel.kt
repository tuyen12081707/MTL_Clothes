package com.example.mtl_clothes.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mtl_clothes.api.res.ProductRes
import java.io.Serializable

@Entity(tableName = "order_table")
class OrderModel : Serializable {
    @PrimaryKey(autoGenerate = true)
    var order_id = 0

    @ColumnInfo(name = "quantity_order")
    var quantity_order = 0
    @ColumnInfo(name = "name")
    var name: String = ""
    @ColumnInfo(name = "typeOf")
    var typeOf: String = ""
    @ColumnInfo(name = "category")
    var category: String = ""
    @ColumnInfo(name = "description")
    var description: String = ""
    @ColumnInfo(name = "price")
    var price: Int = 0
    @ColumnInfo(name = "quantity")
    var quantity: Int = 0
    @ColumnInfo(name = "brand")
    var brand: String = ""
    @ColumnInfo(name = "photos")
    var photos: List<String>? = null
    @ColumnInfo(name = "size")
    var size: List<String>? = null
    @ColumnInfo(name = "id")
    var id: String = ""
    @ColumnInfo(name = "createdAt")
    var createdAt: String = ""
    @ColumnInfo(name = "updatedAt")
    var updatedAt: String = ""
    @ColumnInfo(name = "version")
    var version: Int = 0
}
