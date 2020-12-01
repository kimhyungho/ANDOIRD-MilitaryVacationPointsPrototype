package com.appdev.mvp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.UnsupportedEncodingException
import java.util.*

class LoginActivity : AppCompatActivity() {


    lateinit var emailView: EditText
    lateinit var passwordView: EditText
    lateinit var signUpView: TextView
    lateinit var loginBtn: Button
    lateinit var backgroundView: ConstraintLayout
    lateinit var logoView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
        initListener()
    }

    private fun initView() {
        emailView = email
        passwordView = password
        signUpView = sign_up
        loginBtn = login
        backgroundView = background
        logoView = logoImage
    }

    @SuppressLint("ResourceAsColor")
    private fun initListener() {


        loginBtn.setOnClickListener {
            val uuid = getUuid()
            val email = emailView.text.toString()
            val password = passwordView.text.toString()

            LoginClient(this, uuid, email, password).login().login()
                .enqueue(object : Callback<LoginResult> {
                    override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "네트워크 오류", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<LoginResult>,
                        response: Response<LoginResult>
                    ) {
                        if (response.isSuccessful) {

                            val code = response.body()?.code

                            when (code) {
                                1 -> {
                                    val token = response.body()?.token
                                    val sharedPreference =
                                        getSharedPreferences("info", Context.MODE_PRIVATE)
                                    val editor = sharedPreference.edit()
                                    editor.putString("token", token)
                                    editor.putString("email", email)
                                    editor.putInt("remained_vacation", response.body()!!.remained_vacation)
                                    editor.putInt("remained_day", response.body()!!.remained_day)
                                    editor.commit()
                                    Log.d(
                                        "kkkk",
                                        "" + response.body()?.code + response.body()?.name + response.body()?.code + response.body()?.remained_vacation + response.body()?.remained_day + response.body()?.position + response.body()?.rank
                                    )
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "로그인 성공",
                                        Toast.LENGTH_SHORT
                                    ).show()






                                    val intent =
                                        Intent(this@LoginActivity, MainActivity::class.java)
                                    startActivity(intent)
                                }
                                -400 -> {
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "잘못된 이메일 혹은 패스워드 입니다.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                -401 -> {
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "허용되지 않은 사용자입니다.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        } else {
                            Log.d("confirm", "서버 오류")
                        }
                    }
                })
        }

        signUpView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
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

        Log.d("kkkk", uuid.toString())

        return uuid.toString()
    }
}