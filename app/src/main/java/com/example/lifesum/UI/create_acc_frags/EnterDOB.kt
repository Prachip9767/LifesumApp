package com.example.lifesum.UI.create_acc_frags

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lifesum.R
import kotlinx.android.synthetic.main.fragment_enter_d_o_b.*


class EnterDOB : Fragment(R.layout.fragment_enter_d_o_b) {
    private var goalType: Int? = null
    private var gender: String? = null

//    private var b_date: Int? = null
//    private var b_month: String? = null
//    private var b_year: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.run {
            goalType = getInt("goalType")
            gender = getString("gender")

        }

        btn_next3.setOnClickListener {
            val date = et_dob_date.text.toString()
            val month = et_dob_month.text.toString()
            val year = et_dob_year.text.toString()

            if (date != "" && month != "" && year != "") {

                val bundle = Bundle()
                bundle.putInt("goalType", goalType!!)
                bundle.putString("gender", gender)
                bundle.putInt("b_date", date.toInt())
                bundle.putString("b_month", month)
                bundle.putInt("b_year", year.toInt())

                activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(
                    R.anim.slide_in,  // enter
                    R.anim.fade_out,  // exit
                    R.anim.fade_in,   // popEnter
                    R.anim.slide_out  // popExit
                )
                    ?.add(R.id.createAccountFragmentContainer, EnterHeight::class.java, bundle)
                    ?.addToBackStack("")
                    ?.commit()
            } else
                Toast.makeText(context, "Please enter your DOB", Toast.LENGTH_SHORT).show()
        }
    }
}