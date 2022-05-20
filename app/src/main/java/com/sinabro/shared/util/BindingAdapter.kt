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


    //정답 밑줄로 만들기
    @JvmStatic
    @BindingAdapter("sentence", "answer", requireAll = false)
    fun printProblem(textView: TextView, sentence : String, answer : String) : String{
        return sentence.replace(answer, "____").also { textView.text = it }
    }
}