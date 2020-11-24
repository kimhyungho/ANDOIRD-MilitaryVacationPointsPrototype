package com.appdev.mvp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.UnsupportedEncodingException
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    lateinit var emailView: EditText
    lateinit var passwordView: EditText
    lateinit var signUpBtn: Button
    lateinit var check: Button
    lateinit var okBtn: Button
    lateinit var authCodeView: EditText

    private var authCode: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initView()
        initListener()
    }


    private fun initView() {
        emailView = su_email
        passwordView = su_password
        signUpBtn = su_sign_up
        check = su_check
        okBtn = button
        authCodeView = auth_code
    }

    private fun initListener() {
        okBtn.setOnClickListener {
            if (authCode != null && authCode.toString() == authCodeView.text.toString()) {
                signUpBtn.isEnabled = true
                emailView.isEnabled = false
                check.isEnabled = false
            }
        }

        check.setOnClickListener {
            LoginClient(this, null, emailView.text.toString(), null).login().emailCheck()
                .enqueue(object : Callback<EmailCheckResult> {
                    override fun onFailure(call: Call<EmailCheckResult>, t: Throwable) {
                    }
                    override fun onResponse(
                        call: Call<EmailCheckResult>,
                        response: Response<EmailCheckResult>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(
                                this@SignUpActivity,
                                "이메일이 전송되었습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                            authCodeView.isEnabled = true
                            okBtn.isEnabled = true
                            authCode = response.body()!!.auth_code
                            Log.d("kkkk", response.body()!!.code.toString())
                        } else {

                        }
                    }
                })

        }

        signUpBtn.setOnClickListener {
            val uuid = getUuid()
            val email = emailView.text.toString()
            val password = passwordView.text.toString()
            if (email == "") {
                Toast.makeText(
                    this@SignUpActivity,
                    "이메일을 입력해주세요.",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (!checkEmail(email)) {
                Toast.makeText(
                    this@SignUpActivity,
                    "이메일 형식이 아닙니다.",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (password == "") {
                Toast.makeText(
                    this@SignUpActivity,
                    "비밀번호를 입력해주세요.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                LoginClient(this, uuid, null, null).login()
                    .signUp(SignUpRequest(email, password))
                    .enqueue(object : Callback<SignUpResult> {
                        override fun onFailure(call: Call<SignUpResult>, t: Throwable) {
                            Toast.makeText(
                                this@SignUpActivity,
                                "인터넷 연결 오류",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onResponse(
                            call: Call<SignUpResult>,
                            response: Response<SignUpResult>
                        ) {
                            if (response.isSuccessful) {
                                when (response.body()?.code) {
                                    1 -> {
                                        Toast.makeText(
                                            this@SignUpActivity,
                                            "회원가입 성공",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        finish()
                                    }
                                    -400 -> {
                                        Toast.makeText(
                                            this@SignUpActivity,
                                            "이미 가입된 휴대폰입니다.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                    -401 -> {
                                        Toast.makeText(
                                            this@SignUpActivity,
                                            "권한이 없습니다.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                    -500 -> {
                                        Toast.makeText(
                                            this@SignUpActivity,
                                            "이미 등록된 이메일입니다.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            } else {
                                Toast.makeText(
                                    this@SignUpActivity,
                                    "서버 오류.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
            }

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

        return uuid.toString()
    }

    private fun checkEmail(email: String): Boolean {
        val regex: String = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$"
        val p: Pattern = Pattern.compile(regex)
        val m: Matcher = p.matcher(email)
        return m.matches()
    }
}