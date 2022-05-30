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

    //리스트 힌트
    @JvmStatic
    @BindingAdapter("value", "select", requireAll = false)
    fun listToTextHint(textView: TextView, value: List<String>? , select : Boolean){
        if(select){
            textView.text = "단어 정의 힌트 보기"
        }else{
            value?.joinToString("," + "\n")?.replace(",","\n").also { textView.text = it }
        }
    }

    //어휘 학습 정의 구분
    @JvmStatic
    @BindingAdapter("definition", "division", requireAll = false)
    fun vocaLearningDefinition(textView: TextView, definition: List<String>? , division : Boolean){
        if(division){
            textView.text = "단어 정의 확인하기"
        }else{
            definition?.joinToString("," + "\n")?.replace(",","\n").also { textView.text = it }
        }
    }

    //어휘 학습 문장 구분
    @JvmStatic
    @BindingAdapter("sentence", "divisionSentence", requireAll = false)
    fun vocaLearningSentence(textView: TextView, sentence: List<String>? , divisionSentence : Boolean){
        if(divisionSentence){
            textView.text = "문장들 확인하기"
        }else{
            sentence?.joinToString("," + "\n")?.replace(",","\n").also { textView.text = it }
        }
    }


    //정답 밑줄로 만들기
    @JvmStatic
    @BindingAdapter("problem", "answer", requireAll = false)
    fun printProblem(textView: TextView, problem : String?, answer : String?) : String{
            return problem?.replace(answer.toString(), "____").also { textView.text = it } ?: ""
    }
}