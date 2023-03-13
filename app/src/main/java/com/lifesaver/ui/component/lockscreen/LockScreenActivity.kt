package com.lifesaver.ui.component.lockscreen

import android.app.KeyguardManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lifesaver.R


class LockScreenActivity : AppCompatActivity() {
    private lateinit var keyguardManager: KeyguardManager
    private lateinit var powerManager: PowerManager
    private lateinit var wakeLock: PowerManager.WakeLock
    private lateinit var decorView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // KeyguardManager 및 PowerManager 객체 가져오기
        keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager

        // PowerManager의 WakeLock 객체를 사용하여 화면 꺼짐 방지
        wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP, "LockScreenActivity:wakeLock")
        wakeLock.acquire()

        // 상태바, 소프트키 숨기기
        decorView = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = uiOptions

        // KeyguardManager의 lockNow() 메서드를 호출하여 락스크린 활성화
        keyguardManager.newKeyguardLock("LockScreenActivity:keyguard").disableKeyguard()
        keyguardManager.requestDismissKeyguard(this, null)

        // 액티비티 내용 설정
        setContentView(R.layout.activity_lock_screen)
    }

    override fun onDestroy() {
        super.onDestroy()

        // WakeLock 객체 해제
        if (wakeLock.isHeld) {
            wakeLock.release()
        }

        // Keyguard 비활성화
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            keyguardManager.requestDismissKeyguard(this, null)
        } else {
            @Suppress("DEPRECATION")
            keyguardManager.exitKeyguardSecurely(null)
        }
    }
}
