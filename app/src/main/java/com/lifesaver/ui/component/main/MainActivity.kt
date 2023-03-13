package com.lifesaver.ui.component.main

import android.content.Intent
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
    }

    override fun onResume() {
        super.onResume()
        getDataUiUpdate()
    }

    private fun initGoEditActivityButton() {
        binding.goInputActivityButton.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initDeleteButton() {
        binding.deleteButton.setOnClickListener {
            deleteData()
        }
    }
    private fun getDataUiUpdate() {
        with(LifeSaverPreferences) {
            binding.nameValueTextView.text = getUserName()
            binding.birthdateValueTextView.text = getBirthdate()
            binding.bloodTypeValueTextView.text = getBloodType()
            binding.emergencyTellValueTextView.text = getEmergencyContact()
            val notice = getNotice()

            binding.noticeTextView.isVisible = notice.isNullOrEmpty().not()
            binding.noticeValueTextView.isVisible = notice.isNullOrEmpty().not()
            if(!notice.isNullOrEmpty()) {
                binding.noticeValueTextView.text = notice
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