package fastcampus.part1.chapter4.ui.component.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import fastcampus.part1.chapter4.data.LifeSaverPreferences
import fastcampus.part1.chapter4.databinding.ActivityMainBinding
import fastcampus.part1.chapter4.ui.component.base.BaseActivity
import fastcampus.part1.chapter4.ui.component.edit.EditActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        initGoEditActivityButton()
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
}