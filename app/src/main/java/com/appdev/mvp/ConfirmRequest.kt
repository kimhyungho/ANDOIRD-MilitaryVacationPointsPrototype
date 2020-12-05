package com.appdev.mvp

import java.io.Serializable

class ConfirmRequest(
    var day: String,
    var point: String,
    var fcm_token: String
) : Serializable