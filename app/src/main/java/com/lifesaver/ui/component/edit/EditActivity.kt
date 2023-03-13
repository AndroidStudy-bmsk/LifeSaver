package com.lifesaver.ui.component.edit

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import com.lifesaver.R
import com.lifesaver.databinding.ActivityEditBinding
import com.lifesaver.data.LifeSaverPreferences
import com.lifesaver.ui.component.base.BaseActivity

class EditActivity: BaseActivity<ActivityEditBinding>() {

    override fun getViewBinding(): ActivityEditBinding {
        return ActivityEditBinding.inflate(layoutInflater)
    }

    override fun initView() {
        initBinding()
        initSpinner()
        initBirthdateLayer()
        initNoticeCheckBox()
        initSaveButton()
    }

    private fun initBinding() {
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initSpinner() {
        binding.spnBloodType.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.blood_types,
            android.R.layout.simple_list_item_1
        )
    }

    private fun initBirthdateLayer() {
        binding.layerBirthdate.setOnClickListener {
            val listener = OnDateSetListener { _, year, month, dayOfMonth ->
                binding.tvBirthdate.text = "$year-${month.inc()}-$dayOfMonth"
            }
            DatePickerDialog(
                this,
                listener,
                2000,
                1,
                1
            ).show()
        }
    }

    private fun initNoticeCheckBox() {
        binding.cbNotice.setOnCheckedChangeListener { _, isChecked ->
            binding.etNoticeValue.isVisible = isChecked
        }

        binding.etNoticeValue.isVisible = binding.cbNotice.isChecked
    }

    private fun initSaveButton() {
        binding.btnSave.setOnClickListener {
            saveData()
            finish()
        }
    }

    private fun saveData() {
        with(LifeSaverPreferences) {
            putUserName(binding.etNameValue.text.toString())
            putBloodType(fetchBloodType())
            putEmergencyContact(binding.etEmergencyTellValue.text.toString())
            putBirthdate(binding.tvBirthdate.text.toString())
            putNotice(fetchNotice())
        }
        Toast.makeText(this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun fetchBloodType(): String {
        val bloodAlphabet = binding.spnBloodType.selectedItem.toString()
        val bloodSigned = if(binding.rbtnBloodTypePlus.isChecked) "+" else "-"
        return "$bloodSigned$bloodAlphabet"
    }

    private fun fetchNotice(): String {
        return if(binding.cbNotice.isChecked) binding.etNoticeValue.text.toString() else ""
    }
}