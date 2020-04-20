package com.zyh.firstlinecode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zyh.firstlinecode.Lifecycles.LifecyclesActivity
import com.zyh.firstlinecode.viewModel.CountActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linkViewModelDemo.setOnClickListener {
            intent = Intent(this,CountActivity::class.java)
            startActivity(intent)
        }
        linkLifecyclesDemo.setOnClickListener {
            intent = Intent(this,LifecyclesActivity::class.java)
            startActivity(intent)
        }
        linkLiveDataDemo.setOnClickListener {
            intent = Intent(this,com.zyh.firstlinecode.livedata.CountActivity::class.java)
            startActivity(intent)
        }
    }
}
