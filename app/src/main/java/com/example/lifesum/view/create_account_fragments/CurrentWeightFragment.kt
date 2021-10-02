package com.example.lifesum.view.create_account_fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lifesum.R
import kotlinx.android.synthetic.main.fragment_current_weight.*


class CurrentWeightFragment : Fragment(R.layout.fragment_current_weight) {

    private var goalType: Int = 0
    private lateinit var gender: String
    private var bDate: Int = 0
    private lateinit var bMonth: String
    private var bYear: Int = 0
    private var height: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.run {
            goalType = getInt("goalType")
            gender = getString("gender").toString()
            bDate = getInt("b_date")
            bMonth = getString("b_month").toString()
            bYear = getInt("b_year")
            height = getInt("height")
        }

        btn_next5.setOnClickListener {
            val curr_weight = et_curr_weight.text.toString()
            if (curr_weight != "") {

                val bundle = Bundle()
                bundle.putInt("goalType", goalType!!)
                bundle.putString("gender", gender)
                bundle.putInt("b_date", bDate)
                bundle.putString("b_month", bMonth)
                bundle.putInt("b_year", bYear)
                bundle.putInt("height", height)
                bundle.putInt("curr_weight", curr_weight.toInt())

                activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(
                    R.anim.slide_in,  // enter
                    R.anim.fade_out,  // exit
                    R.anim.fade_in,   // popEnter
                    R.anim.slide_out  // popExit
                )
                    ?.add(
                        R.id.createAccountFragmentContainer,
                        GoalWeightFragment::class.java,
                        bundle
                    )
                    ?.addToBackStack("")
                    ?.commit()
            } else
                Toast.makeText(context, "Please enter your weight", Toast.LENGTH_SHORT).show()

        }
    }
}