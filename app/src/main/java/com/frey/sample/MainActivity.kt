package com.frey.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.frey.treasure.device.DeviceInfo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        macAddress.text = DeviceInfo.getMacAddress(this)
    }
}
