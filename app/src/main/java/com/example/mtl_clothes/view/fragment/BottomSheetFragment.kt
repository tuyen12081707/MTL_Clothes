package com.example.mtl_clothes.view.fragment

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import com.example.mtl_clothes.databinding.DialogCheckOutBinding
import com.example.mtl_clothes.ultis.Common
import com.example.mtl_clothes.ultis.ConvertCurrency
import com.example.mtl_clothes.view.activity.AddressActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment(var mContext: Context, var totalCost: Int) : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "ModalBottomSheet"

        var clickAdd = MutableLiveData<Boolean>(false)
        var clickAddress = MutableLiveData<Boolean>(false)
        fun newInstance(mContext: Context, totalCost: Int): BottomSheetFragment {
            val args = Bundle()

            val fragment = BottomSheetFragment(mContext, totalCost)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var binding: DialogCheckOutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        binding = DialogCheckOutBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun setWhiteNavigationBar(@NonNull dialog: Dialog) {
        val window = dialog.window
        if (window != null) {
            val metrics = DisplayMetrics()
            window.windowManager.defaultDisplay.getMetrics(metrics)
            val dimDrawable = GradientDrawable()
            // ...customize your dim effect here
            val navigationBarDrawable = GradientDrawable()
            navigationBarDrawable.shape = GradientDrawable.RECTANGLE
            navigationBarDrawable.setColor(Color.BLACK)
            val layers = arrayOf<Drawable>(dimDrawable, navigationBarDrawable)
            val windowBackground = LayerDrawable(layers)
            windowBackground.setLayerInsetTop(1, metrics.heightPixels)
            window.setBackgroundDrawable(windowBackground)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.tvTotalPrices.isSelected = true
        binding.tvTotalPrices.text = ConvertCurrency.getInstance().convertToUSD(totalCost.toFloat())
        binding.lnAddress.setOnClickListener {
            clickAddress.postValue(true)
        }
        binding.ivClose.setOnClickListener {
            if(dialog?.isShowing==true){
                dialog?.dismiss()
            }
        }
        binding.btnAdd.setOnClickListener {
            if (Common.getShippingCity(mContext) != "" && Common.getShippingAddress(mContext) != "" && Common.getTelephone(
                    mContext
                ) != ""
            ) {
                clickAdd.postValue(true)
                dialog?.dismiss()
            } else {
                Toast.makeText(mContext, "Please choose address before order!", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        setWhiteNavigationBar(dialog)
        return dialog

    }

}