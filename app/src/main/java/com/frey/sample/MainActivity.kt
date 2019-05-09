package com.frey.sample

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.frey.treasure.device.DeviceInfo
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        macAddress.text = DeviceInfo.getMacAddress(this)
        imei.text = DeviceInfo.getImei(this)
        imsi.text = DeviceInfo.getImsi(this)
        resolution.text = DeviceInfo.getScreenResolution(this, DeviceInfo.SCREEN_RESOLUTION_WIDTH_FIRST)

        println("===================================${Build.BOARD}  ${Build.BOOTLOADER} ${Build.CPU_ABI} ${Build.VERSION.INCREMENTAL}" +
                "${Build.VERSION.INCREMENTAL}")
        println("基带版本 " + DeviceInfo.getBasebandVersion())
        println(Build.MANUFACTURER)
        println(DeviceInfo.getBrand())
        println(DeviceInfo.getModel())
        println(DeviceInfo.getReleaseVersion())
    }
}
