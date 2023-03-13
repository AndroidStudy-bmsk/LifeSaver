package com.lifesaver.ui.component.main

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.view.isVisible
import com.lifesaver.databinding.ActivityMainBinding
import com.lifesaver.data.LifeSaverPreferences
import com.lifesaver.ui.component.base.BaseActivity
import com.lifesaver.ui.component.edit.EditActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        initGoEditActivityButton()
        initDeleteButton()
        initEmergencyContactLayer()
    }

    override fun onResume() {
        super.onResume()
        getDataUiUpdate()
    }

    private fun initGoEditActivityButton() {
        binding.btnGoInputActivity.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initDeleteButton() {
        binding.btnDelete.setOnClickListener {
            deleteData()
        }
    }

    private fun initEmergencyContactLayer() {
        binding.layerEmergencyContact.setOnClickListener {
            // 암시적 인텐트
            with(Intent(Intent.ACTION_VIEW)) {
                val phoneNumber = binding.tvEmergencyTellValue.text.toString()
                    .replace("-", "")
                data = Uri.parse("tel:$phoneNumber")
                startActivity(this)
            }
        }
    }

    private fun getDataUiUpdate() {
        with(LifeSaverPreferences) {
            binding.tvNameValue.text = getUserName()
            binding.tvBirthdateValue.text = getBirthdate()
            binding.tvBloodTypeValue.text = getBloodType()
            binding.tvEmergencyTellValue.text = getEmergencyContact()
            val notice = getNotice()

            binding.tvNotice.isVisible = notice.isNullOrEmpty().not()
            binding.tvNoticeValue.isVisible = notice.isNullOrEmpty().not()
            if (!notice.isNullOrEmpty()) {
                binding.tvNoticeValue.text = notice
            }
        }
    }

    private fun deleteData() {
        with(LifeSaverPreferences) {
            clear()
            getDataUiUpdate()
        }
        Toast.makeText(this, "초기화를 완료했습니다.", Toast.LENGTH_SHORT).show()
    }
}