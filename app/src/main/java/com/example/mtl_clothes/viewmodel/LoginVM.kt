package com.example.mtl_clothes.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.mtl_clothes.api.req.LoginReq
import com.example.mtl_clothes.api.res.LoginRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginVM : CommonVM() {

    var liveDataLogin: MutableLiveData<LoginRes> = MutableLiveData(null)
    fun postLogin(mContext: Context, email: String, password: String) {
        var call = getApi().getApiLogin(LoginReq(email, password))
        call.enqueue(object : Callback<LoginRes> {
            override fun onResponse(call: Call<LoginRes>, response: Response<LoginRes>) {
                Log.d("login===",response.message().toString())
                if (response.isSuccessful) {
                    liveDataLogin.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<LoginRes>, t: Throwable) {
                Log.d("login2===",t.message.toString())
                Toast.makeText(mContext,"Login Failed${t.message}",Toast.LENGTH_SHORT).show()
            }

        })
    }

}