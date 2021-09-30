package com.example.lifesum.UI

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.lifesum.R
import com.example.lifesum.onBackPressForFragment
import kotlinx.android.synthetic.main.dummy_layout.*


class DiaryFragment(val onBackPress: onBackPressForFragment) : Fragment(R.layout.dummy_layout) {
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

        select_date.setOnClickListener {
            val calenderFragment = CalenderFragment(onBackPress)
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.mainActivityFragmentContainer, calenderFragment)
                ?.addToBackStack("")
                ?.commit()
        }

    }

}
