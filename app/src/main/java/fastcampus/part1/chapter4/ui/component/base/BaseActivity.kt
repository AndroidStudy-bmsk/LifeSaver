package fastcampus.part1.chapter4.ui.component.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: VB

    abstract fun getViewBinding(): VB

    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()

        initView()
    }

    private fun initBinding() {
        binding = getViewBinding()
        setContentView(binding.root)
    }
}