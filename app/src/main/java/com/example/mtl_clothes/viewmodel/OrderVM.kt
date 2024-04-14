package com.example.mtl_clothes.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.mtl_clothes.api.req.OrderReq
import com.example.mtl_clothes.api.res.OrderRes
import com.example.mtl_clothes.ultis.Common
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderVM : CommonVM() {

    var liveOrder = MutableLiveData<OrderRes>(null)
    fun makeOrder(mContext: Context, orderReq: OrderReq) {
            var call = getApi().postOrder( orderReq)
            call.enqueue(object : Callback<OrderRes> {
                override fun onResponse(call: Call<OrderRes>, response: Response<OrderRes>) {
                    Log.d("sucess===","sucess===")

                    if (response.isSuccessful) {
                        Log.d("sucess2===","sucess2===")
                        liveOrder.postValue(response.body())

                    }else{
                        Log.d("failedSucess==",response.code().toString())
                    }

                }

                override fun onFailure(call: Call<OrderRes>, t: Throwable) {
                    Log.d("failed===","failed===")
                    Toast.makeText(mContext,t.message.toString(),Toast.LENGTH_SHORT).show()
                }

            })

        Log.d("hello1===","hello1===")
    }
}