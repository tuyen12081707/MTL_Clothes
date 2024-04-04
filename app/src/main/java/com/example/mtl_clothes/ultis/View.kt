package com.example.mtl_clothes.ultis

import android.view.View


fun View.setVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}