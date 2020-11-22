package com.appdev.mvp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_frist.*
import java.io.UnsupportedEncodingException
import java.util.*

class FirstActivity : AppCompatActivity() {

    lateinit var normalLoginBtn: TextView
    lateinit var managerLoginBtn: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frist)

        initView()
        initListener()
    }

    private fun initView() {
        normalLoginBtn = normal_login_Btn
        managerLoginBtn = manager_login_Btn
    }

    private fun initListener() {
        normalLoginBtn.setOnClickListener {
            val uuid = getUuid()
            Toast.makeText(this, uuid, Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }


    @SuppressLint("HardwareIds")
    private fun getUuid(): String {

        var uuid: UUID? = null
        val androidId = Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
        uuid = if (androidId == null || androidId.isEmpty() || androidId == "9774d56d682e549c") {
            UUID.randomUUID()
        } else {
            try {
                UUID.nameUUIDFromBytes(androidId.toByteArray(Charsets.UTF_8))
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
                UUID.randomUUID()
            }
        }

        Toast.makeText(this, uuid.toString(), Toast.LENGTH_SHORT).show()
        Log.d("getUuid", uuid.toString())
        return uuid.toString()


    }


}