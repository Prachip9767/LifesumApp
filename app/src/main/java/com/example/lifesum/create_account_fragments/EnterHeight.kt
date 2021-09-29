package com.example.lifesum.create_account_fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lifesum.R
import kotlinx.android.synthetic.main.fragment_enter_height.*

class EnterHeight : Fragment(R.layout.fragment_enter_height) {
    private var goalType: Int? = null
    private var gender: String? = null
    private var bDate: Int = 0
    private lateinit var bMonth: String
    private var bYear: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.run {
            goalType = getInt("goalType")
            gender = getString("gender")
            bDate = getInt("b_date")
            bMonth = getString("b_month").toString()
            bYear = getInt("b_year")
        }

        btn_next4.setOnClickListener {
            val height = et_current_height.text.toString()
            if (height != "") {

                val bundle = Bundle()
                bundle.putInt("goalType", goalType!!)
                bundle.putString("gender", gender)
                bundle.putInt("b_date", bDate)
                bundle.putString("b_month", bMonth)
                bundle.putInt("b_year", bYear)
                bundle.putInt("height", height.toInt())

                activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(
                    R.anim.slide_in,  // enter
                    R.anim.fade_out,  // exit
                    R.anim.fade_in,   // popEnter
                    R.anim.slide_out  // popExit
                )
                    ?.add(
                        R.id.createAccountFragmentContainer,
                        CurrentWeightFragment::class.java,
                        bundle
                    )
                    ?.addToBackStack("")
                    ?.commit()
            } else
                Toast.makeText(context, "Please enter your height", Toast.LENGTH_SHORT).show()
        }
    }
}