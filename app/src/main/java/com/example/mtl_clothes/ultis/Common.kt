package com.example.mtl_clothes.ultis

import android.content.Context

object Common {

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