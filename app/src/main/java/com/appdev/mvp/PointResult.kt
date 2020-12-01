package com.appdev.mvp

import java.io.Serializable
import java.util.*

class PointResult (
    var data: List<Point>
): Serializable

class Point(
    var id: Int,
    var email: String,
    var get_point_date: Date,
    var point_reason: String,
    var point: Int
) : Serializable