package com.sinabro.shared.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.sinabro.R
import com.sinabro.databinding.DialogProcessBinding

class CustomDialog(val context : Context) {

    private val dialog = Dialog(context)
    fun progressDialog(): Dialog {
        val binding = DataBindingUtil.inflate<DialogProcessBinding>(
            LayoutInflater.from(context),
            R.layout.dialog_process,
            null,
            false
        )

        dialog.setContentView(binding.root)
        dialog.setCancelable(false)     // 로딩창 꺼지지 않도록
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))     // 다이얼로그 투명한 배경 적용
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)                       // 다이얼로그 외부 영역 투명
        dialog.show()

        return dialog
    }
}