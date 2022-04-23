package com.sinabro.presentation.ui.subjectselect

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.sinabro.R
import com.sinabro.databinding.ActivitySubjectSelectBinding
import com.sinabro.presentation.base.BaseActivity


class SubjectSelectActivity : BaseActivity<ActivitySubjectSelectBinding>(R.layout.activity_subject_select) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSpinner()
        checkSpinnerItem(binding.spinnerSubjectSelect)
        checkSpinnerItem(binding.spinnerSubjectSelectUnit)
    }

    //스피너 생성
    private fun initSpinner(){
        binding.spinnerSubjectSelect.adapter = ArrayAdapter.createFromResource(this, R.array.subject_select,R.layout.item_spinner)
        binding.spinnerSubjectSelectUnit.adapter = ArrayAdapter.createFromResource(this, R.array.subject_unit, R.layout.item_spinner)
    }

    //스피너 아이템 선택
    private fun checkSpinnerItem(view : Spinner){
        view.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
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
}