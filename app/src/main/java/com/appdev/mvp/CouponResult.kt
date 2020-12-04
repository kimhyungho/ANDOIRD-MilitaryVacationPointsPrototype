package com.appdev.mvp

import java.io.Serializable
import java.util.*


class CouponResult(
    var data: List<Coupon>
) : Serializable

class Coupon(
    var check: Boolean,           // 휴가 승인 여부
    var start_date: String,        // 휴가 시작 날짜
    var end_date: String,          // 휴가 마지막 날짜
    var use_point: Int             // 사용한 포인트
) : Serializable