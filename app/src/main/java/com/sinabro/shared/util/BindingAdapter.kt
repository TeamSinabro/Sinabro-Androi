package com.sinabro.shared.util

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapter {

    //리스트 String 변환
    @JvmStatic
    @BindingAdapter("listToText")
    fun listToText(textView: TextView, value: List<String>? ) : String? {
        return value?.joinToString("," + "\n")?.replace(",","\n").also { textView.text = it }
    }


}