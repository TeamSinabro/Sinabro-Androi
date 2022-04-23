package com.sinabro.presentation.ui.subjectselect

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.sinabro.R
import com.sinabro.databinding.ActivitySubjectSelectBinding
import com.sinabro.presentation.base.BaseActivity
import com.sinabro.presentation.ui.main.MainActivity


class SubjectSelectActivity :
    BaseActivity<ActivitySubjectSelectBinding>(R.layout.activity_subject_select) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSpinner()
        clickOkBtn()
        checkSpinnerItem(binding.spinnerSubjectSelect)
        checkSpinnerItem(binding.spinnerSubjectSelectUnit)
    }

    //스피너 생성
    private fun initSpinner() {
        with(binding) {
            imgSpinnerSubjectSelectArrow.bringToFront()
            imgSpinnerSubjectSelectUnitArrow.bringToFront()
            spinnerSubjectSelect.adapter = ArrayAdapter.createFromResource(this@SubjectSelectActivity, R.array.subject_select, R.layout.item_spinner)
            spinnerSubjectSelectUnit.adapter = ArrayAdapter.createFromResource(this@SubjectSelectActivity, R.array.subject_unit, R.layout.item_spinner)
        }

    }

    //스피너 아이템 선택
    private fun checkSpinnerItem(view: Spinner) {
        view.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    //확인 버튼 클릭시 메인으로
    private fun clickOkBtn(){
        binding.textSubjectSelectOk.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}