package com.example.lifesum.view.create_account_fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.lifesum.R
import kotlinx.android.synthetic.main.fragment_choose_goal.*

class ChooseGoalFragment : Fragment(R.layout.fragment_choose_goal) {

    var goalType = 0;
    var isSomethingSelected = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_lose_weight.setOnClickListener {
            tv_lose_weight.background =
                ContextCompat.getDrawable(this.requireActivity(), R.drawable.button_selected_effect)
            goalType = 1
            isSomethingSelected = true

            tv_maintain_weight.background =
                ContextCompat.getDrawable(this.requireActivity(), R.drawable.button_default_effect)
            tv_gain_weight.background =
                ContextCompat.getDrawable(this.requireActivity(), R.drawable.button_default_effect)
        }

        tv_maintain_weight.setOnClickListener {
            tv_maintain_weight.background =
                ContextCompat.getDrawable(this.requireActivity(), R.drawable.button_selected_effect)
            goalType = 2
            isSomethingSelected = true
            tv_gain_weight.background =
                ContextCompat.getDrawable(this.requireActivity(), R.drawable.button_default_effect)
            tv_lose_weight.background =
                ContextCompat.getDrawable(this.requireActivity(), R.drawable.button_default_effect)
        }

        tv_gain_weight.setOnClickListener {
            tv_gain_weight.background =
                ContextCompat.getDrawable(this.requireActivity(), R.drawable.button_selected_effect)
            goalType = 3
            isSomethingSelected = true
            tv_lose_weight.background =
                ContextCompat.getDrawable(this.requireActivity(), R.drawable.button_default_effect)
            tv_maintain_weight.background =
                ContextCompat.getDrawable(this.requireActivity(), R.drawable.button_default_effect)
        }






        btn_next1.setOnClickListener {
            if (isSomethingSelected) {

                Log.d("rkpsx7", goalType.toString())
                val bundle = Bundle()
                bundle.putInt("goalType", goalType)

                activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(
                    R.anim.slide_in,  // enter
                    R.anim.fade_out,  // exit
                    R.anim.fade_in,   // popEnter
                    R.anim.slide_out  // popExit
                )
                    ?.add(
                        R.id.createAccountFragmentContainer,
                        SelectGenderFragment::class.java,
                        bundle
                    )
                    ?.addToBackStack("")
                    ?.commit()
            } else
                Toast.makeText(context, "Please select an option", Toast.LENGTH_SHORT).show()
        }
    }
}