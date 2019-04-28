package com.seta.common.extensions

import com.seta.common.logs.LogX
import java.util.*

/**
 * Created by SETA_WORK on 2017/7/24.
 */
object DateUtils {
    fun getYMD(year: Int, month: Int, day: Int): String {
        return "$year-$month-$day"
    }

    fun getDateByYMD(year: Int, month: Int, day: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        return Date(calendar.timeInMillis)
    }
}

fun Date?.toYMD(): String {
    if (this == null) {
        return ""
    }
    val cal = Calendar.getInstance()
    cal.time = this
    return cal.toYMD()
//    val simpleDateFormat = SimpleDateFormat.getInstance()
//    return simpleDateFormat.format(this)
}

fun Calendar?.toYMD(): String {
    if (this == null) {
        return ""
    }
    val result = "${get(Calendar.YEAR)}-${get(Calendar.MONTH) + 1}-${get(Calendar.DAY_OF_MONTH)}"
    LogX.d("Cal to YMD : $result")
    return result
}