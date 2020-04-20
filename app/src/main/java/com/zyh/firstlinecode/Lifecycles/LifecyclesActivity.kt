package com.zyh.firstlinecode.Lifecycles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zyh.firstlinecode.R

class LifecyclesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycles)
        lifecycle.addObserver(MyObserver())
    }
}
