package fastcampus.part1.chapter4

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class EditActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val message = intent.getStringExtra("intentMessage") ?: "No Message"
        Log.d("IntentMessage", message)
    }
}