package com.example.lifesum.UI

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.lifesum.R
import com.google.android.gms.common.api.internal.LifecycleCallback.getFragment
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.dummy_layout.*
import kotlinx.android.synthetic.main.sample.*
import pl.rspective.pagerdatepicker.adapter.DatePagerFragmentAdapter
import pl.rspective.pagerdatepicker.adapter.DefaultDateAdapter
import pl.rspective.pagerdatepicker.view.RecyclerViewInsetDecoration
import java.text.SimpleDateFormat
import java.util.*


class DiaryFragment : Fragment(R.layout.dummy_layout) {


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        Water.setOnClickListener {
//            Water.playAnimation()
//        }
//        Water2.setOnClickListener {
//            Water2.playAnimation()
//        }
//        Water3.setOnClickListener {
//            Water3.playAnimation()
//        }
//        Water4.setOnClickListener {
//            Water4.playAnimation()
//        }

        val materialDateBuilder: MaterialDatePicker.Builder<Long> =
            MaterialDatePicker.Builder.datePicker()

        materialDateBuilder.setTitleText("Select a date")
        val materialDatePicker = materialDateBuilder.build()




        select_date.setOnClickListener {

            materialDatePicker.show(requireFragmentManager(), "MATERIAL_DATE_PICKER");
        }
        materialDatePicker.addOnPositiveButtonClickListener { selection -> // Get the offset from our timezone and UTC.
            val timeZoneUTC = TimeZone.getDefault()

            val offsetFromUTC = timeZoneUTC.getOffset(Date().time) * -1

            val simpleFormat = SimpleDateFormat("EEEE, dd MMM", Locale.US)
            val date = Date(selection + offsetFromUTC)
            select_date.text = simpleFormat.format(date)
        }



    }


}

