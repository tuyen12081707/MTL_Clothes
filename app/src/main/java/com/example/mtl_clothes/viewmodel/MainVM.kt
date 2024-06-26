package com.example.mtl_clothes.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mtl_clothes.api.res.ProductRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainVM : CommonVM() {
    var listProduct: MutableLiveData<MutableList<ProductRes>> = MutableLiveData()

    fun getAllProduct(context:Context) {

        val call = getApi().getAllProduct()
        call.enqueue(object : Callback<MutableList<ProductRes>> {


            override fun onResponse(
                call: Call<MutableList<ProductRes>>,
                response: Response<MutableList<ProductRes>>
            ) {
                try {
                    Log.d("productres==",response.message().toString())
                    if(response.isSuccessful){
                        Log.d("productres==", response.body()?.size.toString())
                        listProduct.postValue(response.body())
                    }else{
                        Log.d("failed===","failed===")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

            override fun onFailure(call: Call<MutableList<ProductRes>>, t: Throwable) {
                Toast.makeText(context,t.message.toString(),Toast.LENGTH_SHORT).show()
            }

        })
    }
}