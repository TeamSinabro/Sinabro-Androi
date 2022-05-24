package com.sinabro.presentation.base

import android.app.Dialog
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.sinabro.shared.util.CustomDialog

abstract class BaseActivity<T : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int
) : AppCompatActivity() {
    private var _binding: T? = null
    val binding get() = _binding!!
    var loadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutResId)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    fun showLoading() {
        dismissLoading()
        loadingDialog = CustomDialog(this).progressDialog()
    }

    fun dismissLoading() {
        if (loadingDialog != null)
            loadingDialog!!.dismiss()
    }
}