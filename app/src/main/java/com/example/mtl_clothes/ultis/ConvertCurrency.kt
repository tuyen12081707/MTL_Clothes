package com.example.mtl_clothes.ultis

class ConvertCurrency private constructor() {
    companion object {
        private var instance: ConvertCurrency? = null

        fun getInstance(): ConvertCurrency {
            return instance ?: synchronized(this) {
                instance ?: ConvertCurrency().also { instance = it }
            }
        }
    }

    fun convertToUSD(amount: Float): String {
        return "$" + String.format("%.2f", amount.toDouble()) + " USD"
    }
}
