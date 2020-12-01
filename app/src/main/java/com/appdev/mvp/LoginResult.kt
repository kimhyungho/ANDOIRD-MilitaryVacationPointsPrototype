package com.appdev.mvp

import java.io.Serializable

class LoginResult(
    var msg: String,
    var token: String,
    var point: Int,
    var remained_vacation: Int,
    var remained_day: Int,
    var name: String,
    var position: String,
    var rank: String,
    var code: Int
) : Serializable