package com.example.mtl_clothes.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.mtl_clothes.api.req.RegisterReq
import com.example.mtl_clothes.api.res.RegisterRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterVM : CommonVM() {
    var registerLive = MutableLiveData<RegisterRes>()

    fun makeRegister(mContext: Context, registerReq: RegisterReq) {
        val call = getApi().postRegister(registerReq)
        call.enqueue(object : Callback<RegisterRes> {
            override fun onResponse(call: Call<RegisterRes>, response: Response<RegisterRes>) {
                if (response.isSuccessful || response.code() == 200) {
                    registerLive.postValue(response.body())
                } else if (response.code() == 400) {
                    Toast.makeText(mContext, response.message().toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<RegisterRes>, t: Throwable) {
                Toast.makeText(
                    mContext,
                    "Failed to call api" + t.message.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }
}