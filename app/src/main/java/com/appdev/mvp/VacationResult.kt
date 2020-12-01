package com.appdev.mvp

import java.io.Serializable
import java.util.*


class VacationResult(
    var data: List<Vacation>
):Serializable

class Vacation(
    var id: Int,
    var email: String,
    var get_vacation_date: Date,
    var vacation_reason: String,
    var vacation: Int
) : Serializable