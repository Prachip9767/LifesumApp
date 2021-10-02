package com.example.lifesum.UI.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.lifesum.R
import com.example.lifesum.UI.activites.onBackPressForFragment
import kotlinx.android.synthetic.main.activity_calander_fragment.*
import java.text.SimpleDateFormat
import java.util.*

class CalenderFragment(val onBackPress: onBackPressForFragment) :
    Fragment(R.layout.activity_calander_fragment) {
    var date: String = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val monthsName = arrayOf(
            "Jan",
            "Feb",
            "Mar",
            "April",
            "May",
            "June",
            "July",
            "Aug",
            "Sep",
            "Oct",
            "Nov",
            "Dec"
        )

        var date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
//        val xDate = arrayOf(date.split("-"))
//        tv_year_cal.text = xDate[0].toString()
//        val dayName = getNameOfDay(
//            xDate[0].toString().toInt(),
//            xDate[1].toString().toInt(),
//            xDate[2].toString().toInt()
//        )
//        tv_date_w_day_cal.text = "$dayName, ${xDate[2]} ${monthsName[xDate[1].toString().toInt()]}"


        calenderView.setOnDateChangeListener { calendarView, i, i2, i3 ->
            date = "$i3/${i2 + 1}/$i"
            tv_year_cal.text = "$i"

            val dayName = getNameOfDay(i, i2 + 1, i3)
            tv_date_w_day_cal.text = "$dayName, $i3 ${monthsName[i2]}"

        }

        btn_ok_cal.setOnClickListener {

        }

        btn_cancel_cal.setOnClickListener {
            onBackPress.onCancelPressForFragment()
        }

    }

    fun getNameOfDay(year: Int, mon: Int, day: Int): String {
        // First convert to Date. This is one of the many ways.
        val dateString = java.lang.String.format("%d-%d-%d", year, mon, day)
        val date: Date = SimpleDateFormat("yyyy-M-d").parse(dateString)

        // Then get the day of week from the Date based on specific locale.
        val dayOfWeek: String = SimpleDateFormat("EEE", Locale.ENGLISH).format(date)
        return dayOfWeek
    }

//    fun getNameOfDay(year: Int, dayOfYear: Int): String? {
//        val calendar = Calendar.getInstance()
//        calendar[Calendar.YEAR] = year
//        calendar[Calendar.DAY_OF_YEAR] = dayOfYear
//        val days =
//            arrayOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
//        val dayIndex = calendar[Calendar.DAY_OF_WEEK]
//        return days[dayIndex - 1]
//    }
}

