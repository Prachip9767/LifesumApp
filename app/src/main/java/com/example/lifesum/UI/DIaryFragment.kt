package com.example.lifesum.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lifesum.R
import kotlinx.android.synthetic.main.activity_main.*


class DIaryFragment : Fragment(R.layout.fragment_d_iary) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Water.setOnClickListener {
            Water.playAnimation()
        }
        Water2.setOnClickListener {
            Water2.playAnimation()
        }
        Water3.setOnClickListener {
            Water3.playAnimation()
        }
        Water4.setOnClickListener {
            Water4.playAnimation()
        }
    }
    }
