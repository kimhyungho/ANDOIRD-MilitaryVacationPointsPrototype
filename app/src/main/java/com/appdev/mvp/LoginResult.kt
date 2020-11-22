package com.appdev.mvp

import java.io.Serializable

class LoginResult(
    var msg: String,
    var token: String,
    var code: Int
) : Serializable