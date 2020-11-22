package com.appdev.mvp

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.style.ForegroundColorSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class EventDecorator(
    private val context: Context,
    private val drawable: Int,
    private val dates: List<CalendarDay>
) : DayViewDecorator {
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return dates.contains(day)
    }

    override fun decorate(view: DayViewFacade?) {
        view!!.setSelectionDrawable(context.resources.getDrawable(drawable))
        view.addSpan(ForegroundColorSpan(Color.WHITE))
    }
}