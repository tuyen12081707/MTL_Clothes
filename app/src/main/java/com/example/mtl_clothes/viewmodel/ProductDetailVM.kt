package com.example.mtl_clothes.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.mtl_clothes.api.res.ProductRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ProductDetailVM:CommonVM() {
    var productDetail: MutableLiveData<ProductRes> = MutableLiveData()

    fun getAllProduct(context: Context,id:String) {

        val call = getApi().getProductDetail(id)
        call.enqueue(object : Callback<ProductRes> {


            override fun onResponse(
                call: Call<ProductRes>,
                response: Response<ProductRes>
            ) {
                try {
                    Log.d("productres==",response.message().toString())
                    if(response.isSuccessful){
                        Log.d("productDetaill==", response.body()?.size.toString())
                        productDetail.postValue(response.body())
                    }else{
                        Log.d("failed===","failed===")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

            override fun onFailure(call: Call<ProductRes>, t: Throwable) {
                Toast.makeText(context,t.message.toString(), Toast.LENGTH_SHORT).show()
            }


        })
    }

}