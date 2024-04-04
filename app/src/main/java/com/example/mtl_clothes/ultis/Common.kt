package com.example.mtl_clothes.ultis

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.mtl_clothes.model.ProductModel
import com.example.mtl_clothes.view.activity.MainActivity
import java.io.Serializable

object Common {

    val KEY_PRODUCT_ORDER = "KEY_PRODUCT_ORDER"
    @JvmStatic
    var listOrder: MutableList<ProductModel> = mutableListOf()
    fun <T : Serializable?> getSerializable(intent: Intent, key: String, m_class: Class<T>): T {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra(key, m_class)!!
        else
            intent.getSerializableExtra(key) as T
    }
    fun getLoginSuccess(mContext: Context): Boolean {
        val preferences =
            mContext.getSharedPreferences(mContext.packageName, Context.MODE_MULTI_PROCESS)
        return preferences.getBoolean("KEY_SUCCESS", false)
    }

    fun setLoginSuccess(context: Context, flag: Boolean) {
        val preferences =
            context.getSharedPreferences(context.packageName, Context.MODE_MULTI_PROCESS)
        preferences.edit().putBoolean("KEY_SUCCESS", flag).apply()
    }

}