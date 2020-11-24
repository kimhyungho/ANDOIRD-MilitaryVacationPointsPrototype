package com.appdev.mvp

import java.io.Serializable

class EmailCheckResult(
    var msg: String,   // -401 잘못된 uuid
    var auth_code: Int,
    var code: Int
) : Serializable