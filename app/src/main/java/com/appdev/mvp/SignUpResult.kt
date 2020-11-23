package com.appdev.mvp

import java.io.Serializable

class SignUpResult(
    var msg: String,   // -401 잘못된 uuid
    var code: Int      // 1 성공
) : Serializable