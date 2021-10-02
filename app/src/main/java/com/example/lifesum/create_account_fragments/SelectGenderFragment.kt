package com.example.lifesum.create_account_fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.lifesum.R
import kotlinx.android.synthetic.main.fragment_select_gender.*

class SelectGenderFragment : Fragment(R.layout.fragment_select_gender) {

    private var goalType: Int? = null
    private var gender: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.run {
            goalType = getInt("goalType")
        }

        tv_btn_male.setOnClickListener {
            tv_btn_male.background =
                ContextCompat.getDrawable(this.requireActivity(), R.drawable.button_selected_effect)
            gender = "male"
            tv_btn_female.background =
                ContextCompat.getDrawable(this.requireActivity(), R.drawable.button_default_effect)
        }

        tv_btn_female.setOnClickListener {
            tv_btn_female.background =
                ContextCompat.getDrawable(this.requireActivity(), R.drawable.button_selected_effect)
            gender = "female"
            tv_btn_male.background =
                ContextCompat.getDrawable(this.requireActivity(), R.drawable.button_default_effect)
        }




        btn_next2.setOnClickListener {
            if (gender != null) {
                val bundle = Bundle()
                bundle.putInt("goalType", goalType!!)
                bundle.putString("gender", gender)

                activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(
                    R.anim.slide_in,  // enter
                    R.anim.fade_out,  // exit
                    R.anim.fade_in,   // popEnter
                    R.anim.slide_out  // popExit
                )
                    ?.add(R.id.createAccountFragmentContainer, EnterDOB::class.java, bundle)
                    ?.addToBackStack("")
                    ?.commit()
            } else
                Toast.makeText(context, "Please select your gender", Toast.LENGTH_SHORT).show()
        }
    }

}