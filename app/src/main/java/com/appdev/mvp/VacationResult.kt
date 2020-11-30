package com.appdev.mvp

import java.io.Serializable
import java.util.*

class VacationResult(
    var date: Date,
    var title: String,
    var vacation: Int
) : Serializable